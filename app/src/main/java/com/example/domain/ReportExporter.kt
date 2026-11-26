package com.example.domain

import com.example.model.ExamAttempt
import com.example.model.QuizKind
import com.example.model.UserProfile
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ReportExporter {

    private val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("id", "ID"))

    fun buildSummaryText(
        profile: UserProfile,
        progress: OverallProgress,
        attempts: List<ExamAttempt>,
        nGain: NGainResult?
    ): String {
        val sb = StringBuilder()
        sb.appendLine("🌿 *RAPOR BELAJAR MANDIRI AGRIBUN* 🌿")
        sb.appendLine("Media Belajar Agribisnis Perkebunan SMK Kelas X (Fase E)")
        sb.appendLine("────────────────────────────")
        sb.appendLine("👤 *Nama Siswa:* ${profile.name.ifBlank { "Siswa SMK" }}")
        sb.appendLine("🏫 *Kelas:* ${profile.className.ifBlank { "Fase E" }}")
        sb.appendLine("📅 *Tanggal:* ${dateFormat.format(Date())}")
        sb.appendLine("────────────────────────────")
        sb.appendLine("📊 *KEMAJUAN BELAJAR:*")
        sb.appendLine("• Modul Dibaca: ${progress.readSectionsCount}/${progress.totalSections} bagian (${progress.readingProgressPercent}%)")
        sb.appendLine("• Studi Kasus: ${progress.caseStudiesCompletedCount}/${progress.totalCaseStudies} kasus tuntas")
        sb.appendLine("• Progres Total: ${progress.overallPercent}%")
        sb.appendLine("────────────────────────────")
        sb.appendLine("🎯 *HASIL ASESMEN:*")

        val pretest = attempts.filter { it.kind == QuizKind.PRETEST }.maxByOrNull { it.takenAt }
        val posttest = attempts.filter { it.kind == QuizKind.POSTTEST }.maxByOrNull { it.takenAt }

        sb.appendLine("• Pretest Diagnostik: ${pretest?.score ?: "-"} / 100")
        sb.appendLine("• Posttest Sumatif: ${posttest?.score ?: "-"} / 100")

        if (nGain != null) {
            sb.appendLine("• N-Gain Hake: ${nGain.nGainScore} (${nGain.nGainPercentage})")
            sb.appendLine("• Kategori N-Gain: *${nGain.category.label}*")
        }

        sb.appendLine("────────────────────────────")
        sb.appendLine("📝 *NILAI KUIS FORMATIF UNIT:*")
        val u1Score = attempts.filter { it.unitId == "unit-1" }.maxOfOrNull { it.score }
        val u2Score = attempts.filter { it.unitId == "unit-2" }.maxOfOrNull { it.score }
        val u3Score = attempts.filter { it.unitId == "unit-3" }.maxOfOrNull { it.score }
        val u4Score = attempts.filter { it.unitId == "unit-4" }.maxOfOrNull { it.score }

        sb.appendLine("• Unit 1 (Sistem Agribisnis): ${u1Score?.let { "$it (KKTP 75: ${if (it >= 75) "Tuntas" else "Belum"})" } ?: "Belum Kuis"}")
        sb.appendLine("• Unit 2 (Faktor Produksi): ${u2Score?.let { "$it (KKTP 75: ${if (it >= 75) "Tuntas" else "Belum"})" } ?: "Belum Kuis"}")
        sb.appendLine("• Unit 3 (Pembiakan Tanaman): ${u3Score?.let { "$it (KKTP 75: ${if (it >= 75) "Tuntas" else "Belum"})" } ?: "Belum Kuis"}")
        sb.appendLine("• Unit 4 (Pascapanen & K3LH): ${u4Score?.let { "$it (KKTP 75: ${if (it >= 75) "Tuntas" else "Belum"})" } ?: "Belum Kuis"}")

        val unlockedBadges = progress.badges.filter { it.isUnlocked }.joinToString(", ") { it.title }
        if (unlockedBadges.isNotBlank()) {
            sb.appendLine("────────────────────────────")
            sb.appendLine("🎖️ *Lencana Diraih:* $unlockedBadges")
        }

        sb.appendLine("────────────────────────────")
        sb.appendLine("Dicetak mandiri secara offline melalui AgriBun v1.0")
        return sb.toString()
    }

    fun buildCsv(
        profile: UserProfile,
        attempts: List<ExamAttempt>,
        nGain: NGainResult?
    ): String {
        val sb = StringBuilder()
        sb.appendLine("Nama,Kelas,Jenis Asesmen,Unit,Skor,Benar,Total Soal,Status KKTP (75),Waktu")
        for (a in attempts) {
            val dateStr = dateFormat.format(Date(a.takenAt))
            val kktpStatus = if (a.score >= 75) "TUNTAS" else "BELUM TUNTAS"
            val unitStr = a.unitId ?: "-"
            sb.appendLine("\"${profile.name}\",\"${profile.className}\",\"${a.kind.name}\",\"$unitStr\",${a.score},${a.correct},${a.total},\"$kktpStatus\",\"$dateStr\"")
        }
        if (nGain != null) {
            sb.appendLine("\"${profile.name}\",\"${profile.className}\",\"N-GAIN\",\"KESELURUHAN\",${(nGain.nGainScore * 100).toInt()},-,-,\"${nGain.category.name}\",\"-\"")
        }
        return sb.toString()
    }
}
