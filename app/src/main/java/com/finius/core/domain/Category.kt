package com.finius.core.domain

import com.finius.R
import com.finius.core.CategoryEntity

enum class CategoryIcon(val iconRes: Int) {
    ShoppingBag(iconRes = R.drawable.shopping_bag_fill),
    ForkKnife(iconRes = R.drawable.fork_knife_fill),
    Airplane(iconRes = R.drawable.airplane_fill),
    CarProfile(iconRes = R.drawable.car_profile_fill),
    Gift(iconRes = R.drawable.gift_fill),
    Currency(iconRes = R.drawable.currency_dolar_fill),
}

data class Category(
    val id: String,
    val title: String,
    val icon: CategoryIcon,
) {
    companion object {
        fun fakeCategory(
            id: String = "id",
            title: String = "Compras",
            icon: CategoryIcon = CategoryIcon.ShoppingBag
        ) = Category(
            id = id,
            title = title,
            icon = icon
        )

        fun fakeCategories() = listOf(
            fakeCategory(
                id = "1",
                title = "Alimentação",
                icon = CategoryIcon.ForkKnife
            ),
            fakeCategory(
                id = "2",
                title = "Viagem",
                icon = CategoryIcon.Airplane
            ),
            fakeCategory(
                id = "3",
                title = "Transporte",
                icon = CategoryIcon.CarProfile
            ),
            fakeCategory(
                id = "4",
                title = "Compras",
                icon = CategoryIcon.ShoppingBag
            ),
            fakeCategory(
                id = "5",
                title = "Presente",
                icon = CategoryIcon.Gift
            ),
        )
    }
}

fun CategoryEntity.toCategory() = Category(
    id = id,
    title = title,
    icon = icon
)