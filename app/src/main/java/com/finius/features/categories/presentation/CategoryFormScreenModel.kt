package com.finius.features.categories.presentation

import androidx.compose.foundation.text.input.TextFieldState
import cafe.adriel.voyager.core.model.ScreenModel
import com.finius.core.data.CategoryRepository
import com.finius.core.domain.CategoryIcon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

data class CategoryFormScreenState(
    val title: TextFieldState = TextFieldState(),
    val selectedIcon: CategoryIcon? = null
)

class CategoryFormScreenModel(
    private val categoryRepository: CategoryRepository
) : ScreenModel {

    private var _uiState = MutableStateFlow(CategoryFormScreenState())
    val uiState = _uiState.asStateFlow()

    fun setIcon(icon: CategoryIcon) {
        _uiState.update {
            it.copy(
                selectedIcon = icon
            )
        }
    }

    fun createCategory() = with(_uiState.value) {
        categoryRepository.create(
            id = UUID.randomUUID().toString(),
            title = title.text.toString(),
            icon = selectedIcon ?: throw Error("Icon cannot be null.")
        )
    }
}