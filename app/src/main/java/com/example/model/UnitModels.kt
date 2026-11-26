package com.example.model

enum class TP(val number: Int, val label: String) {
    TP1(1, "TP 1: Sistem & Manajemen Agribisnis"),
    TP2(2, "TP 2: Faktor Produksi & OPT"),
    TP3(3, "TP 3: Pembiakan Tanaman"),
    TP4(4, "TP 4: Produksi, Pascapanen & K3LH")
}

data class Media(
    val type: String, // "image", "infographic", "video"
    val title: String,
    val src: String? = null,
    val youtubeUrl: String? = null,
    val caption: String? = null
)

data class Section(
    val id: String,
    val heading: String,
    val body: String,
    val keyPoints: List<String> = emptyList(),
    val media: List<Media> = emptyList(),
    val infographicId: String? = null
)

data class LearningUnit(
    val id: String,
    val tp: TP,
    val title: String,
    val summary: String,
    val iconName: String,
    val sections: List<Section>,
    val quizId: String,
    val estimatedMinutes: Int = 15
)
