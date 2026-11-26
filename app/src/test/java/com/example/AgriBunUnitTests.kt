package com.example

import com.example.domain.CalculateNGainUseCase
import com.example.domain.CalculateProgressUseCase
import com.example.domain.GradeQuizUseCase
import com.example.domain.GradeQuizUseCase.UserAnswer
import com.example.domain.NGainCategory
import com.example.model.ExamAttempt
import com.example.model.MatchingPair
import com.example.model.Question
import com.example.model.QuestionType
import com.example.model.QuizKind
import com.example.model.TP
import com.example.model.UnitProgress
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class AgriBunUnitTests {

    private val gradeQuizUseCase = GradeQuizUseCase()
    private val calculateNGainUseCase = CalculateNGainUseCase()
    private val calculateProgressUseCase = CalculateProgressUseCase()

    @Test
    fun testGradeQuiz_MultipleChoicePassesKKTP() {
        val questions = listOf(
            Question(
                id = "q1",
                unitId = "unit-1",
                tp = TP.TP1,
                type = QuestionType.MULTIPLE_CHOICE,
                prompt = "Apa itu agribisnis?",
                options = listOf("A", "B", "C", "D"),
                correctIndex = 1,
                explanation = "Penjelasan Q1"
            ),
            Question(
                id = "q2",
                unitId = "unit-1",
                tp = TP.TP1,
                type = QuestionType.MULTIPLE_CHOICE,
                prompt = "Apa itu hulu?",
                options = listOf("A", "B", "C", "D"),
                correctIndex = 0,
                explanation = "Penjelasan Q2"
            ),
            Question(
                id = "q3",
                unitId = "unit-1",
                tp = TP.TP1,
                type = QuestionType.MULTIPLE_CHOICE,
                prompt = "Apa itu hilir?",
                options = listOf("A", "B", "C", "D"),
                correctIndex = 2,
                explanation = "Penjelasan Q3"
            ),
            Question(
                id = "q4",
                unitId = "unit-1",
                tp = TP.TP1,
                type = QuestionType.MULTIPLE_CHOICE,
                prompt = "Apa itu usahatani?",
                options = listOf("A", "B", "C", "D"),
                correctIndex = 3,
                explanation = "Penjelasan Q4"
            )
        )

        // 3 of 4 correct = 75% -> KKTP pass
        val answers = mapOf(
            "q1" to UserAnswer.MultipleChoice(1), // Correct
            "q2" to UserAnswer.MultipleChoice(0), // Correct
            "q3" to UserAnswer.MultipleChoice(2), // Correct
            "q4" to UserAnswer.MultipleChoice(0)  // Wrong (correct is 3)
        )

        val result = gradeQuizUseCase.grade(QuizKind.UNIT_QUIZ, "unit-1", questions, answers)

        assertEquals(75, result.score)
        assertEquals(3, result.correctCount)
        assertEquals(4, result.totalQuestions)
        assertTrue(result.passedKKTP)
        assertEquals(1, result.wrongQuestions.size)
        assertEquals("q4", result.wrongQuestions[0].first.id)
    }

    @Test
    fun testGradeQuiz_MatchingAndOrdering() {
        val matchQ = Question(
            id = "q-match",
            unitId = "unit-2",
            tp = TP.TP2,
            type = QuestionType.MATCHING,
            prompt = "Pasangkan OPT",
            pairs = listOf(
                MatchingPair("Sawit", "Ulat Api"),
                MatchingPair("Karet", "Jamur Akar Putih")
            ),
            explanation = "OPT Perkebunan"
        )

        val orderQ = Question(
            id = "q-order",
            unitId = "unit-1",
            tp = TP.TP1,
            type = QuestionType.ORDERING,
            prompt = "Urutkan POAC",
            correctOrder = listOf("Planning", "Organizing", "Actuating", "Controlling"),
            explanation = "Siklus POAC"
        )

        val answers = mapOf(
            "q-match" to UserAnswer.Matching(mapOf("Sawit" to "Ulat Api", "Karet" to "Jamur Akar Putih")),
            "q-order" to UserAnswer.Ordering(listOf("Planning", "Organizing", "Actuating", "Controlling"))
        )

        val result = gradeQuizUseCase.grade(QuizKind.UNIT_QUIZ, "unit-1", listOf(matchQ, orderQ), answers)
        assertEquals(100, result.score)
        assertEquals(2, result.correctCount)
        assertTrue(result.passedKKTP)
        assertTrue(result.wrongQuestions.isEmpty())
    }

    @Test
    fun testCalculateNGain_Categories() {
        // High N-Gain: Pretest = 30, Posttest = 85
        // (85 - 30) / (100 - 30) = 55 / 70 = 0.785... >= 0.70
        val highResult = calculateNGainUseCase.calculate(30, 85)
        assertNotNull(highResult)
        assertEquals(NGainCategory.TINGGI, highResult?.category)
        assertEquals(0.79, highResult!!.nGainScore, 0.01)

        // Medium N-Gain: Pretest = 40, Posttest = 70
        // (70 - 40) / (100 - 40) = 30 / 60 = 0.50
        val medResult = calculateNGainUseCase.calculate(40, 70)
        assertNotNull(medResult)
        assertEquals(NGainCategory.SEDANG, medResult?.category)
        assertEquals(0.50, medResult!!.nGainScore, 0.01)

        // Low N-Gain: Pretest = 30, Posttest = 40
        // (40 - 30) / (100 - 30) = 10 / 70 = 0.14 < 0.30
        val lowResult = calculateNGainUseCase.calculate(30, 40)
        assertNotNull(lowResult)
        assertEquals(NGainCategory.RENDAH, lowResult?.category)
        assertEquals(0.14, lowResult!!.nGainScore, 0.01)

        // Edge case: Pretest = 100, Posttest = 100
        val perfectResult = calculateNGainUseCase.calculate(100, 100)
        assertNotNull(perfectResult)
        assertEquals(1.0, perfectResult!!.nGainScore, 0.0)
        assertEquals(NGainCategory.TINGGI, perfectResult.category)

        // Edge case: Posttest lower than Pretest
        val lowerResult = calculateNGainUseCase.calculate(50, 40)
        assertNotNull(lowerResult)
        assertEquals(0.0, lowerResult!!.nGainScore, 0.0)
        assertEquals(NGainCategory.RENDAH, lowerResult.category)
    }

    @Test
    fun testCalculateProgress_BadgesAndOverall() {
        val unit1 = com.example.data.source.content.UnitsContent.units[0]
        val units = listOf(unit1)

        val progressMap = mapOf(
            "unit-1" to UnitProgress(
                unitId = "unit-1",
                sectionsRead = unit1.sections.map { it.id }.toSet(),
                caseStudyDone = true,
                bestScore = 80
            )
        )

        val attempts = listOf(
            ExamAttempt(
                id = "a1",
                kind = QuizKind.UNIT_QUIZ,
                unitId = "unit-1",
                score = 80,
                correct = 4,
                total = 5
            )
        )

        val overall = calculateProgressUseCase.calculate(units, progressMap, attempts)

        assertEquals(100, overall.readingProgressPercent)
        assertEquals(1, overall.caseStudiesCompletedCount)
        assertEquals(100, overall.overallPercent)

        // Verify badges
        val starterBadge = overall.badges.find { it.id == "badge-starter" }
        assertTrue(starterBadge?.isUnlocked == true)

        val kktpBadge = overall.badges.find { it.id == "badge-kktp" }
        assertTrue(kktpBadge?.isUnlocked == true)
    }
}
