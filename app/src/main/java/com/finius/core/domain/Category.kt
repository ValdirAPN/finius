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

        fun fakeCategories() = listOf(
            fakeCategory(
                id = "1",
                title = "Alimentação",
                iconRes = R.drawable.fork_knife_fill
            ),
            fakeCategory(
                id = "2",
                title = "Viagem",
                iconRes = R.drawable.airplane_fill
            ),
            fakeCategory(
                id = "3",
                title = "Transporte",
                iconRes = R.drawable.car_profile_fill
            ),
            fakeCategory(
                id = "4",
                title = "Compras",
                iconRes = R.drawable.shopping_bag_fill
            ),
            fakeCategory(
                id = "5",
                title = "Presente",
                iconRes = R.drawable.gift_fill
            ),
        )
    }
}
