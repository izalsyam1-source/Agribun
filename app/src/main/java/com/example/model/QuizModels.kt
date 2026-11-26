package com.example.model

enum class QuizKind(val title: String) {
    PRETEST("Pretest (Asesmen Diagnostik Awal)"),
    UNIT_QUIZ("Kuis Formatif Unit"),
    POSTTEST("Posttest (Asesmen Sumatif Akhir)")
}

data class ExamAttempt(
    val id: String,
    val kind: QuizKind,
    val unitId: String? = null,
    val score: Int,
    val correct: Int,
    val total: Int,
    val takenAt: Long = System.currentTimeMillis()
)

data class QuizResult(
    val score: Int,
    val correctCount: Int,
    val totalQuestions: Int,
    val passedKKTP: Boolean,
    val wrongQuestions: List<Pair<Question, String>> = emptyList(),
    val attempt: ExamAttempt
)
