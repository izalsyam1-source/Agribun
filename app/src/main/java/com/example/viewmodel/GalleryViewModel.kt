package com.example.viewmodel

import androidx.lifecycle.ViewModel
import com.example.data.repository.IContentRepository
import com.example.model.Commodity
import com.example.model.GlossaryTerm
import com.example.model.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class GallerySubTab {
    KOMODITAS,
    GLOSARIUM
}

data class GalleryData(
    val activeSubTab: GallerySubTab = GallerySubTab.KOMODITAS,
    val commodities: List<Commodity>,
    val selectedCommodity: Commodity? = null,
    val compareList: List<Commodity> = emptyList(), // up to 2 for comparison
    val isComparing: Boolean = false,
    val glossaryTerms: List<GlossaryTerm>,
    val searchQuery: String = "",
    val activeLetterFilter: String = "SEMUA"
)

class GalleryViewModel(
    private val contentRepository: IContentRepository
) : ViewModel() {

    private val allCommodities = contentRepository.getCommodities()
    private val _uiState = MutableStateFlow<UiState<GalleryData>>(
        UiState.Success(
            GalleryData(
                commodities = allCommodities,
                glossaryTerms = contentRepository.getGlossary()
            )
        )
    )
    val uiState: StateFlow<UiState<GalleryData>> = _uiState.asStateFlow()

    fun switchSubTab(tab: GallerySubTab) {
        val curr = currentData()
        _uiState.value = UiState.Success(curr.copy(activeSubTab = tab))
    }

    fun selectCommodity(commodity: Commodity?) {
        val curr = currentData()
        _uiState.value = UiState.Success(curr.copy(selectedCommodity = commodity))
    }

    fun toggleCompareCommodity(commodity: Commodity) {
        val curr = currentData()
        val currentCompare = curr.compareList.toMutableList()
        if (currentCompare.any { it.id == commodity.id }) {
            currentCompare.removeAll { it.id == commodity.id }
        } else {
            if (currentCompare.size >= 2) {
                currentCompare.removeAt(0)
            }
            currentCompare.add(commodity)
        }
        val isComparing = currentCompare.size == 2
        _uiState.value = UiState.Success(curr.copy(compareList = currentCompare, isComparing = isComparing))
    }

    fun clearComparison() {
        val curr = currentData()
        _uiState.value = UiState.Success(curr.copy(compareList = emptyList(), isComparing = false))
    }

    fun updateSearchQuery(query: String) {
        val curr = currentData()
        val filtered = contentRepository.searchGlossary(query, curr.activeLetterFilter)
        _uiState.value = UiState.Success(
            curr.copy(
                searchQuery = query,
                glossaryTerms = filtered
            )
        )
    }

    fun filterByLetter(letter: String) {
        val curr = currentData()
        val filtered = contentRepository.searchGlossary(curr.searchQuery, letter)
        _uiState.value = UiState.Success(
            curr.copy(
                activeLetterFilter = letter,
                glossaryTerms = filtered
            )
        )
    }

    private fun currentData(): GalleryData {
        return (uiState.value as? UiState.Success)?.data ?: GalleryData(
            commodities = allCommodities,
            glossaryTerms = contentRepository.getGlossary()
        )
    }
}
