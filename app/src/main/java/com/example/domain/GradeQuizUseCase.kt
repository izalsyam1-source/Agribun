package com.example.domain

import com.example.model.ExamAttempt
import com.example.model.MatchingPair
import com.example.model.Question
import com.example.model.QuestionType
import com.example.model.QuizKind
import com.example.model.QuizResult
import java.util.UUID

class GradeQuizUseCase {

    sealed interface UserAnswer {
        data class MultipleChoice(val selectedIndex: Int) : UserAnswer
        data class Matching(val userPairs: Map<String, String>) : UserAnswer
        data class Ordering(val userOrder: List<String>) : UserAnswer
    }

    fun grade(
        kind: QuizKind,
        unitId: String?,
        questions: List<Question>,
        userAnswers: Map<String, UserAnswer>
    ): QuizResult {
        if (questions.isEmpty()) {
            val emptyAttempt = ExamAttempt(
                id = UUID.randomUUID().toString(),
                kind = kind,
                unitId = unitId,
                score = 0,
                correct = 0,
                total = 0
            )
            return QuizResult(
                score = 0,
                correctCount = 0,
                totalQuestions = 0,
                passedKKTP = false,
                wrongQuestions = emptyList(),
                attempt = emptyAttempt
            )
        }

        var correctCount = 0
        val wrongList = mutableListOf<Pair<Question, String>>()

        for (q in questions) {
            val ans = userAnswers[q.id]
            val isCorrect = when (q.type) {
                QuestionType.MULTIPLE_CHOICE -> {
                    val mc = ans as? UserAnswer.MultipleChoice
                    mc != null && q.correctIndex != null && mc.selectedIndex == q.correctIndex
                }
                QuestionType.MATCHING -> {
                    val match = ans as? UserAnswer.Matching
                    if (match != null && q.pairs.isNotEmpty()) {
                        q.pairs.all { pair -> match.userPairs[pair.left] == pair.right }
                    } else false
                }
                QuestionType.ORDERING -> {
                    val ord = ans as? UserAnswer.Ordering
                    if (ord != null && q.correctOrder.isNotEmpty()) {
                        ord.userOrder == q.correctOrder
                    } else false
                }
            }

            if (isCorrect) {
                correctCount++
            } else {
                wrongList.add(q to q.explanation)
            }
        }

        val total = questions.size
        val score = ((correctCount.toDouble() / total.toDouble()) * 100.0).toInt().coerceIn(0, 100)
        val passed = score >= 75 // KKTP SMK >= 75

        val attempt = ExamAttempt(
            id = UUID.randomUUID().toString(),
            kind = kind,
            unitId = unitId,
            score = score,
            correct = correctCount,
            total = total,
            takenAt = System.currentTimeMillis()
        )

        return QuizResult(
            score = score,
            correctCount = correctCount,
            totalQuestions = total,
            passedKKTP = passed,
            wrongQuestions = wrongList,
            attempt = attempt
        )
    }
}
