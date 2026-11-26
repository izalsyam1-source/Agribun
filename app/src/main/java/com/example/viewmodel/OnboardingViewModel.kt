package com.example.viewmodel

import androidx.lifecycle.ViewModel
import com.example.data.repository.IProgressRepository
import com.example.model.UiState
import com.example.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class OnboardingData(
    val nameInput: String = "",
    val classInput: String = "",
    val isSaving: Boolean = false,
    val isSaved: Boolean = false
)

class OnboardingViewModel(
    private val progressRepository: IProgressRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<OnboardingData>>(
        UiState.Success(OnboardingData())
    )
    val uiState: StateFlow<UiState<OnboardingData>> = _uiState.asStateFlow()

    fun onNameChanged(name: String) {
        val curr = currentData()
        _uiState.value = UiState.Success(curr.copy(nameInput = name))
    }

    fun onClassChanged(className: String) {
        val curr = currentData()
        _uiState.value = UiState.Success(curr.copy(classInput = className))
    }

    fun saveAndContinue(onSuccess: (takePretest: Boolean) -> Unit, takePretest: Boolean) {
        val curr = currentData()
        val name = curr.nameInput.trim()
        val className = curr.classInput.trim()

        if (name.isBlank()) {
            _uiState.value = UiState.Error("Nama murid wajib diisi agar rapor tercatat.")
            return
        }

        progressRepository.saveProfile(
            name = name,
            className = if (className.isBlank()) "Kelas X Perkebunan" else className
        )

        _uiState.value = UiState.Success(curr.copy(isSaved = true))
        onSuccess(takePretest)
    }

    private fun currentData(): OnboardingData {
        return when (val state = _uiState.value) {
            is UiState.Success -> state.data
            else -> OnboardingData()
        }
    }
}
