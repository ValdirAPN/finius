package com.finius.features.transaction.presentation.category

import cafe.adriel.voyager.core.model.ScreenModel
import com.finius.core.data.CategoryRepository
import com.finius.core.domain.Category
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class TransactionCategoryScreenState(
    val categories: List<Category> = emptyList()
)

class TransactionCategoryScreenModel(
    private val categoryRepository: CategoryRepository
) : ScreenModel {

    private var _uiState = MutableStateFlow(TransactionCategoryScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        assemble()
    }

    private fun assemble() {
        val categories = categoryRepository.getAll()
        _uiState.update {
            it.copy(
                categories = categories
            )
        }
    }
}