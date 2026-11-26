package com.example.model

enum class BottomNavTab(val label: String, val iconName: String) {
    PETA("Peta", "map"),
    MATERI("Materi", "menu_book"),
    GALERI("Galeri", "photo_library"),
    LATIHAN("Latihan", "psychology"),
    RAPOR("Rapor", "bar_chart")
}

sealed interface ScreenDestination {
    data object MainTabs : ScreenDestination
    data object Onboarding : ScreenDestination
    data class UnitDetail(val unitId: String, val initialSectionIndex: Int = 0) : ScreenDestination
    data class CaseStudyDetail(val caseStudyId: String) : ScreenDestination
    data class QuizRunner(val kind: QuizKind, val unitId: String? = null) : ScreenDestination
    data class QuizResultScreen(val result: QuizResult) : ScreenDestination
    data object About : ScreenDestination
}
