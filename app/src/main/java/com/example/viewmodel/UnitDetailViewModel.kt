package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.IContentRepository
import com.example.data.repository.IProgressRepository
import com.example.model.LearningUnit
import com.example.model.Section
import com.example.model.UiState
import com.example.model.UnitProgress
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class UnitDetailData(
    val unit: LearningUnit,
    val currentSectionIndex: Int,
    val unitProgress: UnitProgress,
    val isCurrentSectionRead: Boolean,
    val isAllSectionsRead: Boolean
) {
    val currentSection: Section?
        get() = unit.sections.getOrNull(currentSectionIndex)
    val hasNext: Boolean
        get() = currentSectionIndex < unit.sections.size - 1
    val hasPrev: Boolean
        get() = currentSectionIndex > 0
}

class UnitDetailViewModel(
    private val unitId: String,
    initialSectionIndex: Int = 0,
    private val contentRepository: IContentRepository,
    private val progressRepository: IProgressRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<UnitDetailData>>(UiState.Loading)
    val uiState: StateFlow<UiState<UnitDetailData>> = _uiState.asStateFlow()

    private val _currentIndex = MutableStateFlow(initialSectionIndex)

    init {
        loadUnit()
    }

    private fun loadUnit() {
        val unit = contentRepository.getUnit(unitId)
        if (unit == null) {
            _uiState.value = UiState.Error("Unit pembelajaran tidak ditemukan.")
            return
        }

        viewModelScope.launch {
            progressRepository.progressFlow.collectLatest { progressMap ->
                val progress = progressMap[unitId] ?: UnitProgress(unitId = unitId)
                val idx = _currentIndex.value.coerceIn(0, (unit.sections.size - 1).coerceAtLeast(0))
                val currSec = unit.sections.getOrNull(idx)
                val isRead = currSec != null && progress.sectionsRead.contains(currSec.id)
                val allRead = unit.sections.all { progress.sectionsRead.contains(it.id) }

                _uiState.value = UiState.Success(
                    UnitDetailData(
                        unit = unit,
                        currentSectionIndex = idx,
                        unitProgress = progress,
                        isCurrentSectionRead = isRead,
                        isAllSectionsRead = allRead
                    )
                )
            }
        }
    }

    fun nextSection() {
        val curr = currentData() ?: return
        if (curr.hasNext) {
            _currentIndex.value = curr.currentSectionIndex + 1
            loadUnit()
        }
    }

    fun prevSection() {
        val curr = currentData() ?: return
        if (curr.hasPrev) {
            _currentIndex.value = curr.currentSectionIndex - 1
            loadUnit()
        }
    }

    fun jumpToSection(index: Int) {
        val curr = currentData() ?: return
        if (index in 0 until curr.unit.sections.size) {
            _currentIndex.value = index
            loadUnit()
        }
    }

    fun markCurrentSectionRead() {
        val curr = currentData() ?: return
        val sec = curr.currentSection ?: return
        progressRepository.markSectionRead(unitId, sec.id)
    }

    private fun currentData(): UnitDetailData? {
        return (uiState.value as? UiState.Success)?.data
    }
}
