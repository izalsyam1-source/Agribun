package com.example.model

/**
 * Standard UiState representation following explicit state pattern:
 * Loading, Success, Error, Empty
 */
sealed interface UiState<out T> {
    data object Loading : UiState<Nothing>
    data class Success<out T>(val data: T) : UiState<T>
    data class Error(val message: String) : UiState<Nothing>
    data object Empty : UiState<Nothing>
}
