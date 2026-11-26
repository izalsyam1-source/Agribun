package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.IProgressRepository
import com.example.model.AppSettings
import com.example.model.BottomNavTab
import com.example.model.ScreenDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class AppNavigationState(
    val currentTab: BottomNavTab = BottomNavTab.PETA,
    val backStack: List<ScreenDestination> = listOf(ScreenDestination.MainTabs),
    val isDarkMode: Boolean? = null,
    val isOnboardingCompleted: Boolean = false
) {
    val currentDestination: ScreenDestination
        get() = backStack.lastOrNull() ?: ScreenDestination.MainTabs
}

class AppNavigationViewModel(
    private val progressRepository: IProgressRepository
) : ViewModel() {

    private val _navState = MutableStateFlow(AppNavigationState())
    val navState: StateFlow<AppNavigationState> = _navState.asStateFlow()

    init {
        viewModelScope.launch {
            progressRepository.settingsFlow.collectLatest { settings ->
                val onboarded = settings.onboardingDone
                _navState.value = _navState.value.copy(
                    isDarkMode = settings.darkMode,
                    isOnboardingCompleted = onboarded,
                    backStack = if (!onboarded && _navState.value.backStack == listOf(ScreenDestination.MainTabs)) {
                        listOf(ScreenDestination.Onboarding)
                    } else _navState.value.backStack
                )
            }
        }
    }

    fun selectTab(tab: BottomNavTab) {
        _navState.value = _navState.value.copy(
            currentTab = tab,
            backStack = listOf(ScreenDestination.MainTabs)
        )
    }

    fun navigateTo(destination: ScreenDestination) {
        val stack = _navState.value.backStack.toMutableList()
        stack.add(destination)
        _navState.value = _navState.value.copy(backStack = stack)
    }

    fun popBack(): Boolean {
        val stack = _navState.value.backStack.toMutableList()
        if (stack.size > 1) {
            stack.removeAt(stack.size - 1)
            _navState.value = _navState.value.copy(backStack = stack)
            return true
        }
        return false
    }

    fun completeOnboarding() {
        val currentSettings = progressRepository.settingsFlow.value
        progressRepository.updateSettings(currentSettings.copy(onboardingDone = true))
        _navState.value = _navState.value.copy(
            isOnboardingCompleted = true,
            backStack = listOf(ScreenDestination.MainTabs)
        )
    }

    fun toggleDarkMode(darkMode: Boolean?) {
        val currentSettings = progressRepository.settingsFlow.value
        progressRepository.updateSettings(currentSettings.copy(darkMode = darkMode))
    }
}
