package com.finius

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.finius.core.data.CategoryRepository
import com.finius.core.domain.Category
import com.finius.features.categories.presentation.DefaultCategoriesStrings
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenModel(
    private val categoryRepository: CategoryRepository
) : ScreenModel {

    fun initialize(defaultCategoriesStrings: DefaultCategoriesStrings, onSuccess: () -> Unit) {
        screenModelScope.launch {
            Category.defaultCategories(defaultCategoriesStrings).forEach { category ->
                with(category) {
                    categoryRepository.create(
                        id = id,
                        title = title,
                        icon = icon
                    )
                }
            }

            delay(2_000)

            onSuccess()
        }
    }
}