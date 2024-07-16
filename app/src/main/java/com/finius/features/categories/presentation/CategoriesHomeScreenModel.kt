package com.finius.features.categories.presentation

import cafe.adriel.voyager.core.model.ScreenModel
import com.finius.core.data.CategoryRepository
import com.finius.core.domain.Category
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class CategoriesHomeScreenState(
    val categories: List<Category> = emptyList()
)

class CategoriesHomeScreenModel(
    private val categoryRepository: CategoryRepository
) : ScreenModel {

    private val _uiState = MutableStateFlow(CategoriesHomeScreenState())
    val uiState = _uiState.asStateFlow()

    fun assemble() {
        val categories = categoryRepository.getAll()
        _uiState.update {
            it.copy(
                categories = categories
            )
        }
    }
}