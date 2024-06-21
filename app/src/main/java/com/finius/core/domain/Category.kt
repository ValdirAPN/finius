package com.finius.core.domain

import com.finius.R

data class Category(
    val id: String,
    val title: String,
    val iconRes: Int,
) {
    companion object {
        fun fakeCategory(
            id: String = "id",
            title: String = "Compras",
            iconRes: Int = R.drawable.shopping_bag_fill
        ) = Category(
            id = id,
            title = title,
            iconRes = iconRes
        )
    }
}
