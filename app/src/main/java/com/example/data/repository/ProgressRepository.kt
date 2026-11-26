package com.example.data.repository

import com.example.data.source.LocalDataSource
import com.example.model.AppSettings
import com.example.model.ExamAttempt
import com.example.model.QuizKind
import com.example.model.UnitProgress
import com.example.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface IProgressRepository {
    val profileFlow: StateFlow<UserProfile>
    val progressFlow: StateFlow<Map<String, UnitProgress>>
    val attemptsFlow: StateFlow<List<ExamAttempt>>
    val settingsFlow: StateFlow<AppSettings>

    fun saveProfile(name: String, className: String)
    fun markSectionRead(unitId: String, sectionId: String)
    fun markCaseStudyDone(unitId: String)
    fun recordQuizScore(attempt: ExamAttempt)
    fun updateSettings(settings: AppSettings)
    fun resetAllData()
    fun getUnitProgress(unitId: String): UnitProgress
}

class ProgressRepository(
    private val localDataSource: LocalDataSource
) : IProgressRepository {

    private val _profileFlow = MutableStateFlow(localDataSource.getProfile())
    override val profileFlow: StateFlow<UserProfile> = _profileFlow.asStateFlow()

    private val _progressFlow = MutableStateFlow(localDataSource.getProgress())
    override val progressFlow: StateFlow<Map<String, UnitProgress>> = _progressFlow.asStateFlow()

    private val _attemptsFlow = MutableStateFlow(localDataSource.getAttempts())
    override val attemptsFlow: StateFlow<List<ExamAttempt>> = _attemptsFlow.asStateFlow()

    private val _settingsFlow = MutableStateFlow(localDataSource.getSettings())
    override val settingsFlow: StateFlow<AppSettings> = _settingsFlow.asStateFlow()

    override fun saveProfile(name: String, className: String) {
        val newProfile = _profileFlow.value.copy(name = name, className = className)
        localDataSource.saveProfile(newProfile)
        _profileFlow.value = newProfile

        // Also mark onboarding done if name provided
        if (name.isNotBlank()) {
            val updated = _settingsFlow.value.copy(onboardingDone = true)
            updateSettings(updated)
        }
    }

    override fun markSectionRead(unitId: String, sectionId: String) {
        val currentMap = _progressFlow.value.toMutableMap()
        val currentUnit = currentMap[unitId] ?: UnitProgress(unitId = unitId)
        val updatedSections = currentUnit.sectionsRead.toMutableSet().apply { add(sectionId) }
        val updatedUnit = currentUnit.copy(sectionsRead = updatedSections)
        currentMap[unitId] = updatedUnit

        localDataSource.saveProgress(currentMap)
        _progressFlow.value = currentMap
    }

    override fun markCaseStudyDone(unitId: String) {
        val currentMap = _progressFlow.value.toMutableMap()
        val currentUnit = currentMap[unitId] ?: UnitProgress(unitId = unitId)
        val updatedUnit = currentUnit.copy(caseStudyDone = true)
        currentMap[unitId] = updatedUnit

        localDataSource.saveProgress(currentMap)
        _progressFlow.value = currentMap
    }

    override fun recordQuizScore(attempt: ExamAttempt) {
        localDataSource.saveAttempt(attempt)
        _attemptsFlow.value = localDataSource.getAttempts()

        // If unit quiz, update bestScore in progressMap
        if (attempt.kind == QuizKind.UNIT_QUIZ && attempt.unitId != null) {
            val currentMap = _progressFlow.value.toMutableMap()
            val currentUnit = currentMap[attempt.unitId] ?: UnitProgress(unitId = attempt.unitId)
            val best = maxOf(currentUnit.bestScore ?: 0, attempt.score)
            val updatedUnit = currentUnit.copy(bestScore = best)
            currentMap[attempt.unitId] = updatedUnit

            localDataSource.saveProgress(currentMap)
            _progressFlow.value = currentMap
        }
    }

    override fun updateSettings(settings: AppSettings) {
        localDataSource.saveSettings(settings)
        _settingsFlow.value = settings
    }

    override fun resetAllData() {
        localDataSource.clearAllData()
        _profileFlow.value = UserProfile()
        _progressFlow.value = emptyMap()
        _attemptsFlow.value = emptyList()
        _settingsFlow.value = AppSettings()
    }

    override fun getUnitProgress(unitId: String): UnitProgress {
        return _progressFlow.value[unitId] ?: UnitProgress(unitId = unitId)
    }
}
