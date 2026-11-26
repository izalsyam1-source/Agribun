package com.example.domain

enum class NGainCategory(val label: String, val description: String) {
    TINGGI("Efektivitas Tinggi", "Peningkatan pemahaman materi sangat signifikan (g ≥ 0,70)"),
    SEDANG("Efektivitas Sedang", "Peningkatan pemahaman materi memadai (0,30 ≤ g < 0,70)"),
    RENDAH("Efektivitas Rendah", "Peningkatan pemahaman materi masih rendah (g < 0,30)"),
    BELUM_TERSEDIA("Belum Tersedia", "Selesaikan pretest dan posttest untuk menghitung N-Gain")
}

data class NGainResult(
    val pretestScore: Int,
    val posttestScore: Int,
    val nGainScore: Double,
    val nGainPercentage: String,
    val category: NGainCategory
)

class CalculateNGainUseCase {

    fun calculate(pretestScore: Int?, posttestScore: Int?): NGainResult? {
        if (pretestScore == null || posttestScore == null) return null

        val pre = pretestScore.coerceIn(0, 100).toDouble()
        val post = posttestScore.coerceIn(0, 100).toDouble()

        val nGain: Double = if (pre >= 100.0) {
            if (post >= 100.0) 1.0 else 0.0
        } else {
            val gain = (post - pre) / (100.0 - pre)
            if (gain < 0.0) 0.0 else gain
        }

        val rounded = (Math.round(nGain * 100.0) / 100.0).coerceIn(0.0, 1.0)
        val category = when {
            rounded >= 0.70 -> NGainCategory.TINGGI
            rounded >= 0.30 -> NGainCategory.SEDANG
            else -> NGainCategory.RENDAH
        }

        val percentStr = "${(rounded * 100).toInt()}%"

        return NGainResult(
            pretestScore = pretestScore,
            posttestScore = posttestScore,
            nGainScore = rounded,
            nGainPercentage = percentStr,
            category = category
        )
    }
}
