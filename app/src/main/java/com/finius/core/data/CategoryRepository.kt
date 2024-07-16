package com.finius.core.data

import com.finius.core.CategoryEntityQueries
import com.finius.core.domain.CategoryIcon
import com.finius.core.domain.toCategory

class CategoryRepository(
    private val categoryEntityQueries: CategoryEntityQueries
) {

    fun getAll() = categoryEntityQueries
        .getAll()
        .executeAsList()
        .map { it.toCategory() }

    fun create(
        id: String,
        title: String,
        icon: CategoryIcon
    ) = categoryEntityQueries
        .insert(
            id = id,
            title = title,
            icon = icon
        )
}