package com.finius.core.data

import com.finius.core.CategoryEntityQueries
import com.finius.core.domain.Category
import com.finius.core.domain.CategoryIcon
import com.finius.core.domain.toCategory

class CategoryRepository(
    private val categoryEntityQueries: CategoryEntityQueries
) {

    fun getAll(): List<Category> =
        categoryEntityQueries
            .getAll()
            .executeAsList()
            .map { it.toCategory() }

    fun create(
        id: String,
        title: String,
        icon: CategoryIcon
    ) = categoryEntityQueries
        .upsert(
            id = id,
            title = title,
            icon = icon
        )
}