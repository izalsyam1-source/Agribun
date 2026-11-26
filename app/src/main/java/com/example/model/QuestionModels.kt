package com.example.model

enum class QuestionType {
    MULTIPLE_CHOICE,
    MATCHING,
    ORDERING
}

data class MatchingPair(
    val left: String,
    val right: String
)

data class Question(
    val id: String,
    val unitId: String,
    val tp: TP,
    val type: QuestionType,
    val prompt: String,
    val options: List<String> = emptyList(),
    val correctIndex: Int? = null,
    val pairs: List<MatchingPair> = emptyList(),
    val correctOrder: List<String> = emptyList(),
    val explanation: String
)

data class CaseStudy(
    val id: String,
    val unitId: String,
    val tp: TP,
    val title: String,
    val scenario: String,
    val questions: List<Question>
)
