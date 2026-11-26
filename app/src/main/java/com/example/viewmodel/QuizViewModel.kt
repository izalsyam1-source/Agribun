package com.example.viewmodel

import androidx.lifecycle.ViewModel
import com.example.data.repository.IContentRepository
import com.example.data.repository.IProgressRepository
import com.example.domain.GradeQuizUseCase
import com.example.domain.GradeQuizUseCase.UserAnswer
import com.example.model.Question
import com.example.model.QuestionType
import com.example.model.QuizKind
import com.example.model.QuizResult
import com.example.model.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class QuizSessionData(
    val kind: QuizKind,
    val unitId: String?,
    val questions: List<Question>,
    val currentIndex: Int = 0,
    // Answers
    val userAnswers: Map<String, UserAnswer> = emptyMap(),
    // Ordering answers draft
    val orderingDrafts: Map<String, List<String>> = emptyMap(),
    // Matching answers draft
    val matchingDrafts: Map<String, Map<String, String>> = emptyMap(),
    val showSubmitConfirmDialog: Boolean = false,
    val result: QuizResult? = null
) {
    val currentQuestion: Question?
        get() = questions.getOrNull(currentIndex)
    val totalQuestions: Int
        get() = questions.size
    val answeredCount: Int
        get() = userAnswers.size
    val isAllAnswered: Boolean
        get() = answeredCount >= totalQuestions
    val isLastQuestion: Boolean
        get() = currentIndex == totalQuestions - 1
    val isFirstQuestion: Boolean
        get() = currentIndex == 0
}

class QuizViewModel(
    val kind: QuizKind,
    val unitId: String?,
    private val contentRepository: IContentRepository,
    private val progressRepository: IProgressRepository,
    private val gradeQuizUseCase: GradeQuizUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<QuizSessionData>>(UiState.Loading)
    val uiState: StateFlow<UiState<QuizSessionData>> = _uiState.asStateFlow()

    init {
        startQuiz()
    }

    fun startQuiz() {
        val rawQuestions = contentRepository.getQuizQuestions(kind, unitId)
        if (rawQuestions.isEmpty()) {
            _uiState.value = UiState.Error("Bank soal tidak ditemukan.")
            return
        }

        // Shuffle questions for randomized attempt
        val shuffledQuestions = rawQuestions.shuffled()

        // Initialize ordering drafts
        val initialOrdering = mutableMapOf<String, List<String>>()
        shuffledQuestions.filter { it.type == QuestionType.ORDERING }.forEach { q ->
            initialOrdering[q.id] = q.correctOrder.shuffled()
        }

        _uiState.value = UiState.Success(
            QuizSessionData(
                kind = kind,
                unitId = unitId,
                questions = shuffledQuestions,
                orderingDrafts = initialOrdering
            )
        )
    }

    fun selectMultipleChoiceOption(optionIndex: Int) {
        val curr = currentData() ?: return
        val q = curr.currentQuestion ?: return
        val map = curr.userAnswers.toMutableMap()
        map[q.id] = UserAnswer.MultipleChoice(optionIndex)
        _uiState.value = UiState.Success(curr.copy(userAnswers = map))
    }

    fun selectMatchingPair(left: String, right: String) {
        val curr = currentData() ?: return
        val q = curr.currentQuestion ?: return

        val drafts = curr.matchingDrafts.toMutableMap()
        val qDraft = (drafts[q.id] ?: emptyMap()).toMutableMap()
        qDraft[left] = right
        drafts[q.id] = qDraft

        val answers = curr.userAnswers.toMutableMap()
        // If all pairs assigned, mark as answered
        if (qDraft.size >= q.pairs.size) {
            answers[q.id] = UserAnswer.Matching(qDraft)
        }

        _uiState.value = UiState.Success(curr.copy(matchingDrafts = drafts, userAnswers = answers))
    }

    fun moveOrderItem(fromIndex: Int, toIndex: Int) {
        val curr = currentData() ?: return
        val q = curr.currentQuestion ?: return
        val list = (curr.orderingDrafts[q.id] ?: emptyList()).toMutableList()
        if (fromIndex in list.indices && toIndex in list.indices) {
            val item = list.removeAt(fromIndex)
            list.add(toIndex, item)
            val drafts = curr.orderingDrafts.toMutableMap()
            drafts[q.id] = list

            val answers = curr.userAnswers.toMutableMap()
            answers[q.id] = UserAnswer.Ordering(list)

            _uiState.value = UiState.Success(curr.copy(orderingDrafts = drafts, userAnswers = answers))
        }
    }

    fun nextQuestion() {
        val curr = currentData() ?: return
        if (!curr.isLastQuestion) {
            _uiState.value = UiState.Success(curr.copy(currentIndex = curr.currentIndex + 1))
        }
    }

    fun prevQuestion() {
        val curr = currentData() ?: return
        if (!curr.isFirstQuestion) {
            _uiState.value = UiState.Success(curr.copy(currentIndex = curr.currentIndex - 1))
        }
    }

    fun jumpToQuestion(index: Int) {
        val curr = currentData() ?: return
        if (index in 0 until curr.totalQuestions) {
            _uiState.value = UiState.Success(curr.copy(currentIndex = index))
        }
    }

    fun showSubmitDialog(show: Boolean) {
        val curr = currentData() ?: return
        _uiState.value = UiState.Success(curr.copy(showSubmitConfirmDialog = show))
    }

    fun submitQuiz() {
        val curr = currentData() ?: return
        val result = gradeQuizUseCase.grade(
            kind = curr.kind,
            unitId = curr.unitId,
            questions = curr.questions,
            userAnswers = curr.userAnswers
        )

        // Persist attempt
        progressRepository.recordQuizScore(result.attempt)

        _uiState.value = UiState.Success(
            curr.copy(
                showSubmitConfirmDialog = false,
                result = result
            )
        )
    }

    private fun currentData(): QuizSessionData? {
        return (uiState.value as? UiState.Success)?.data
    }
}
