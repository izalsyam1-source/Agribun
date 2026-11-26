package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.IContentRepository
import com.example.data.repository.IProgressRepository
import com.example.domain.CalculateNGainUseCase
import com.example.domain.CalculateProgressUseCase
import com.example.domain.NGainResult
import com.example.domain.OverallProgress
import com.example.domain.ReportExporter
import com.example.model.ExamAttempt
import com.example.model.QuizKind
import com.example.model.UiState
import com.example.model.UnitProgress
import com.example.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

data class ReportCardData(
    val profile: UserProfile,
    val overallProgress: OverallProgress,
    val attempts: List<ExamAttempt>,
    val progressMap: Map<String, UnitProgress>,
    val nGain: NGainResult?,
    val showResetConfirmDialog: Boolean = false,
    val shareText: String = "",
    val csvContent: String = ""
)

class ReportCardViewModel(
    private val contentRepository: IContentRepository,
    private val progressRepository: IProgressRepository,
    private val calculateProgressUseCase: CalculateProgressUseCase,
    private val calculateNGainUseCase: CalculateNGainUseCase,
    private val reportExporter: ReportExporter
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<ReportCardData>>(UiState.Loading)
    val uiState: StateFlow<UiState<ReportCardData>> = _uiState.asStateFlow()

    init {
        loadReport()
    }

    private fun loadReport() {
        val units = contentRepository.getUnits()
        viewModelScope.launch {
            combine(
                progressRepository.profileFlow,
                progressRepository.progressFlow,
                progressRepository.attemptsFlow
            ) { profile, progressMap, attempts ->
                val overall = calculateProgressUseCase.calculate(units, progressMap, attempts)

                // Find latest pretest and posttest
                val pretest = attempts.filter { it.kind == QuizKind.PRETEST }.maxByOrNull { it.takenAt }
                val posttest = attempts.filter { it.kind == QuizKind.POSTTEST }.maxByOrNull { it.takenAt }
                val nGain = calculateNGainUseCase.calculate(pretest?.score, posttest?.score)

                val summaryText = reportExporter.buildSummaryText(profile, overall, attempts, nGain)
                val csv = reportExporter.buildCsv(profile, attempts, nGain)

                ReportCardData(
                    profile = profile,
                    overallProgress = overall,
                    attempts = attempts,
                    progressMap = progressMap,
                    nGain = nGain,
                    shareText = summaryText,
                    csvContent = csv
                )
            }.collect { data ->
                _uiState.value = UiState.Success(data)
            }
        }
    }

    fun showResetDialog(show: Boolean) {
        val curr = (uiState.value as? UiState.Success)?.data ?: return
        _uiState.value = UiState.Success(curr.copy(showResetConfirmDialog = show))
    }

    fun confirmResetAllData() {
        progressRepository.resetAllData()
    }
}
