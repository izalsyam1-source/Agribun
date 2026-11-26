package com.example.model

data class UserProfile(
    val name: String = "",
    val className: String = "",
    val createdAt: Long = System.currentTimeMillis()
)

data class UnitProgress(
    val unitId: String,
    val sectionsRead: Set<String> = emptySet(),
    val caseStudyDone: Boolean = false,
    val bestScore: Int? = null
)

data class AppSettings(
    val darkMode: Boolean? = null, // null = follow system
    val onboardingDone: Boolean = false,
    val guideViewed: Boolean = false
)

data class LearningBadge(
    val id: String,
    val title: String,
    val description: String,
    val iconName: String,
    val isUnlocked: Boolean
)
