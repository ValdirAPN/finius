package com.finius.features.categories

import com.finius.Database
import com.finius.core.data.CategoryRepository
import com.finius.features.categories.presentation.CategoriesHomeScreenModel
import com.finius.features.categories.presentation.CategoryFormScreenModel
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val categoryDi = DI.Module("category-module") {
    bindSingleton {
        instance<Database>().categoryEntityQueries
    }

    bindSingleton {
        CategoryRepository(
            categoryEntityQueries = instance()
        )
    }

    bindProvider {
        CategoryFormScreenModel(
            categoryRepository = instance()
        )
    }

    bindProvider {
        CategoriesHomeScreenModel(
            categoryRepository = instance()
        )
    }
}