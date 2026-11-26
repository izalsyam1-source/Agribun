package com.example.domain

import com.example.model.ExamAttempt
import com.example.model.LearningBadge
import com.example.model.LearningUnit
import com.example.model.QuizKind
import com.example.model.UnitProgress

data class OverallProgress(
    val totalSections: Int,
    val readSectionsCount: Int,
    val readingProgressPercent: Int,
    val caseStudiesCompletedCount: Int,
    val totalCaseStudies: Int,
    val overallPercent: Int,
    val badges: List<LearningBadge>,
    val nextUnitIdToLearn: String?,
    val nextSectionIdToLearn: String?
)

class CalculateProgressUseCase {

    fun calculate(
        units: List<LearningUnit>,
        progressMap: Map<String, UnitProgress>,
        attempts: List<ExamAttempt>
    ): OverallProgress {
        val totalSections = units.sumOf { it.sections.size }
        val allReadSectionIds = progressMap.values.flatMap { it.sectionsRead }.toSet()
        val readCount = allReadSectionIds.size
        val readingPercent = if (totalSections > 0) ((readCount * 100) / totalSections) else 0

        val totalCaseStudies = units.size
        val doneCaseStudies = progressMap.values.count { it.caseStudyDone }

        // Combined weight: 60% reading + 20% case studies + 20% quizzes
        val quizPassedCount = units.count { u ->
            val p = progressMap[u.id]
            (p?.bestScore ?: 0) >= 75
        }
        val quizWeight = if (units.isNotEmpty()) ((quizPassedCount * 100) / units.size) else 0
        val caseStudyWeight = if (totalCaseStudies > 0) ((doneCaseStudies * 100) / totalCaseStudies) else 0

        val overall = ((readingPercent * 0.6) + (caseStudyWeight * 0.2) + (quizWeight * 0.2)).toInt().coerceIn(0, 100)

        // Find next unread section
        var nextUnit: String? = null
        var nextSec: String? = null
        for (u in units) {
            val p = progressMap[u.id]
            val unread = u.sections.find { !allReadSectionIds.contains(it.id) }
            if (unread != null) {
                nextUnit = u.id
                nextSec = unread.id
                break
            }
        }

        // Badges calculation
        val hasPassedAnyQuiz = attempts.any { it.score >= 75 }
        val hasPosttestPassed = attempts.any { it.kind == QuizKind.POSTTEST && it.score >= 75 }
        val unit1Done = units.find { it.id == "unit-1" }?.sections?.all { allReadSectionIds.contains(it.id) } == true
        val unit3Done = units.find { it.id == "unit-3" }?.sections?.all { allReadSectionIds.contains(it.id) } == true

        val badges = listOf(
            LearningBadge(
                id = "badge-starter",
                title = "Langkah Awal",
                description = "Mulai membaca materi pertama di AgriBun",
                iconName = "flag",
                isUnlocked = readCount >= 1
            ),
            LearningBadge(
                id = "badge-u1",
                title = "Pelopor Agribisnis",
                description = "Menyelesaikan seluruh modul Sistem & Manajemen Agribisnis (TP 1)",
                iconName = "business_center",
                isUnlocked = unit1Done
            ),
            LearningBadge(
                id = "badge-u3",
                title = "Pakar Pembiakan",
                description = "Tuntas mempelajari teknik pembiakan okulasi, sambung, dan stek (TP 3)",
                iconName = "nature",
                isUnlocked = unit3Done
            ),
            LearningBadge(
                id = "badge-case-solver",
                title = "Solusionis Lapangan",
                description = "Berhasil memecahkan minimal 2 skenario studi kasus perkebunan",
                iconName = "psychology",
                isUnlocked = doneCaseStudies >= 2
            ),
            LearningBadge(
                id = "badge-kktp",
                title = "Lulus KKTP (≥75)",
                description = "Meraih nilai di atas Kriteria Ketercapaian Tujuan Pembelajaran",
                iconName = "verified",
                isUnlocked = hasPassedAnyQuiz
            ),
            LearningBadge(
                id = "badge-master",
                title = "Agribisnis Teladan",
                description = "Menyelesaikan seluruh TP 1-4 dan lulus posttest akhir dengan gemilang",
                iconName = "military_tech",
                isUnlocked = readCount >= totalSections && doneCaseStudies >= 4 && hasPosttestPassed
            )
        )

        return OverallProgress(
            totalSections = totalSections,
            readSectionsCount = readCount,
            readingProgressPercent = readingPercent,
            caseStudiesCompletedCount = doneCaseStudies,
            totalCaseStudies = totalCaseStudies,
            overallPercent = overall,
            badges = badges,
            nextUnitIdToLearn = nextUnit,
            nextSectionIdToLearn = nextSec
        )
    }
}
