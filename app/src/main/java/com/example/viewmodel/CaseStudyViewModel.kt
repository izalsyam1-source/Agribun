package com.example.viewmodel

import androidx.lifecycle.ViewModel
import com.example.data.repository.IContentRepository
import com.example.data.repository.IProgressRepository
import com.example.model.CaseStudy
import com.example.model.Question
import com.example.model.QuestionType
import com.example.model.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class CaseStudyData(
    val caseStudy: CaseStudy,
    val currentQuestionIndex: Int = 0,
    // Answers per question id
    val multipleChoiceAnswers: Map<String, Int> = emptyMap(),
    val matchingAnswers: Map<String, Map<String, String>> = emptyMap(), // questionId -> (left -> right)
    val orderingAnswers: Map<String, List<String>> = emptyMap(), // questionId -> ordered list
    // Feedback per question id
    val feedbackSubmitted: Map<String, Boolean> = emptyMap(),
    val isAnswerCorrect: Map<String, Boolean> = emptyMap(),
    val isCompleted: Boolean = false
) {
    val currentQuestion: Question?
        get() = caseStudy.questions.getOrNull(currentQuestionIndex)
    val totalQuestions: Int
        get() = caseStudy.questions.size
    val isCurrentQuestionSubmitted: Boolean
        get() = feedbackSubmitted[currentQuestion?.id] == true
    val isCurrentQuestionCorrect: Boolean
        get() = isAnswerCorrect[currentQuestion?.id] == true
}

class CaseStudyViewModel(
    val caseStudyId: String,
    private val contentRepository: IContentRepository,
    private val progressRepository: IProgressRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<CaseStudyData>>(UiState.Loading)
    val uiState: StateFlow<UiState<CaseStudyData>> = _uiState.asStateFlow()

    init {
        loadCaseStudy()
    }

    private fun loadCaseStudy() {
        val cs = contentRepository.getCaseStudy(caseStudyId)
        if (cs == null) {
            _uiState.value = UiState.Error("Studi kasus tidak ditemukan.")
            return
        }

        // Initialize ordering answers with shuffled orders so student starts from mixed
        val initialOrdering = mutableMapOf<String, List<String>>()
        cs.questions.filter { it.type == QuestionType.ORDERING }.forEach { q ->
            initialOrdering[q.id] = q.correctOrder.shuffled()
        }

        _uiState.value = UiState.Success(
            CaseStudyData(
                caseStudy = cs,
                orderingAnswers = initialOrdering
            )
        )
    }

    fun selectMultipleChoiceOption(questionId: String, optionIndex: Int) {
        val curr = currentData() ?: return
        val map = curr.multipleChoiceAnswers.toMutableMap()
        map[questionId] = optionIndex
        _uiState.value = UiState.Success(curr.copy(multipleChoiceAnswers = map))
    }

    fun setMatchingPair(questionId: String, left: String, right: String) {
        val curr = currentData() ?: return
        val map = curr.matchingAnswers.toMutableMap()
        val qMap = (map[questionId] ?: emptyMap()).toMutableMap()
        qMap[left] = right
        map[questionId] = qMap
        _uiState.value = UiState.Success(curr.copy(matchingAnswers = map))
    }

    fun reorderItem(questionId: String, fromIndex: Int, toIndex: Int) {
        val curr = currentData() ?: return
        val list = (curr.orderingAnswers[questionId] ?: emptyList()).toMutableList()
        if (fromIndex in list.indices && toIndex in list.indices) {
            val item = list.removeAt(fromIndex)
            list.add(toIndex, item)
            val map = curr.orderingAnswers.toMutableMap()
            map[questionId] = list
            _uiState.value = UiState.Success(curr.copy(orderingAnswers = map))
        }
    }

    fun submitCurrentAnswer() {
        val curr = currentData() ?: return
        val q = curr.currentQuestion ?: return

        val isCorrect = when (q.type) {
            QuestionType.MULTIPLE_CHOICE -> {
                val ans = curr.multipleChoiceAnswers[q.id]
                ans != null && q.correctIndex != null && ans == q.correctIndex
            }
            QuestionType.MATCHING -> {
                val userPairs = curr.matchingAnswers[q.id] ?: emptyMap()
                q.pairs.isNotEmpty() && q.pairs.all { pair -> userPairs[pair.left] == pair.right }
            }
            QuestionType.ORDERING -> {
                val userOrder = curr.orderingAnswers[q.id] ?: emptyList()
                userOrder == q.correctOrder
            }
        }

        val submitted = curr.feedbackSubmitted.toMutableMap().apply { put(q.id, true) }
        val correctMap = curr.isAnswerCorrect.toMutableMap().apply { put(q.id, isCorrect) }

        // Check if all questions completed correctly
        val allDone = curr.caseStudy.questions.all { question ->
            if (question.id == q.id) isCorrect else correctMap[question.id] == true
        }

        if (allDone) {
            progressRepository.markCaseStudyDone(curr.caseStudy.unitId)
        }

        _uiState.value = UiState.Success(
            curr.copy(
                feedbackSubmitted = submitted,
                isAnswerCorrect = correctMap,
                isCompleted = allDone
            )
        )
    }

    fun retryQuestion(questionId: String) {
        val curr = currentData() ?: return
        val submitted = curr.feedbackSubmitted.toMutableMap().apply { remove(questionId) }
        val correctMap = curr.isAnswerCorrect.toMutableMap().apply { remove(questionId) }
        _uiState.value = UiState.Success(
            curr.copy(
                feedbackSubmitted = submitted,
                isAnswerCorrect = correctMap
            )
        )
    }

    fun nextQuestion() {
        val curr = currentData() ?: return
        if (curr.currentQuestionIndex < curr.totalQuestions - 1) {
            _uiState.value = UiState.Success(
                curr.copy(currentQuestionIndex = curr.currentQuestionIndex + 1)
            )
        }
    }

    fun prevQuestion() {
        val curr = currentData() ?: return
        if (curr.currentQuestionIndex > 0) {
            _uiState.value = UiState.Success(
                curr.copy(currentQuestionIndex = curr.currentQuestionIndex - 1)
            )
        }
    }

    private fun currentData(): CaseStudyData? {
        return (uiState.value as? UiState.Success)?.data
    }
}
