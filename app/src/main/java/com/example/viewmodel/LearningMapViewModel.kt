package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.IContentRepository
import com.example.data.repository.IProgressRepository
import com.example.domain.CalculateProgressUseCase
import com.example.domain.OverallProgress
import com.example.model.ExamAttempt
import com.example.model.LearningUnit
import com.example.model.UiState
import com.example.model.UnitProgress
import com.example.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

data class LearningMapData(
    val profile: UserProfile,
    val units: List<LearningUnit>,
    val progressMap: Map<String, UnitProgress>,
    val overallProgress: OverallProgress,
    val showGuideCard: Boolean = true
)

class LearningMapViewModel(
    private val contentRepository: IContentRepository,
    private val progressRepository: IProgressRepository,
    private val calculateProgressUseCase: CalculateProgressUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<LearningMapData>>(UiState.Loading)
    val uiState: StateFlow<UiState<LearningMapData>> = _uiState.asStateFlow()

    private val _guideDismissed = MutableStateFlow(false)

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val units = contentRepository.getUnits()
            combine(
                progressRepository.profileFlow,
                progressRepository.progressFlow,
                progressRepository.attemptsFlow,
                progressRepository.settingsFlow,
                _guideDismissed
            ) { profile, progressMap, attempts, settings, dismissed ->
                val overall = calculateProgressUseCase.calculate(units, progressMap, attempts)
                val showGuide = !settings.guideViewed && !dismissed
                LearningMapData(
                    profile = profile,
                    units = units,
                    progressMap = progressMap,
                    overallProgress = overall,
                    showGuideCard = showGuide
                )
            }.collect { data ->
                _uiState.value = UiState.Success(data)
            }
        }
    }

    fun dismissGuide() {
        _guideDismissed.value = true
        val currSettings = progressRepository.settingsFlow.value
        progressRepository.updateSettings(currSettings.copy(guideViewed = true))
    }

    fun reopenGuide() {
        _guideDismissed.value = false
    }
}
