package com.finius.core.domain

import com.finius.R
import com.finius.core.CategoryEntity
import com.finius.features.categories.presentation.DefaultCategoriesStrings

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

        fun defaultCategories(strings: DefaultCategoriesStrings) = listOf(
            Category(
                id = "shopping",
                title = strings.shopping,
                icon = CategoryIcon.ShoppingBag
            ),
            Category(
                id = "food",
                title = strings.food,
                icon = CategoryIcon.ForkKnife
            ),
            Category(
                id = "travel",
                title = strings.travel,
                icon = CategoryIcon.Airplane
            ),
            Category(
                id = "transport",
                title = strings.transport,
                icon = CategoryIcon.CarProfile
            ),
            Category(
                id = "gift",
                title = strings.gift,
                icon = CategoryIcon.Gift
            ),
            Category(
                id = "salary",
                title = strings.salary,
                icon = CategoryIcon.Currency
            ),
        )

        fun fakeCategory(
            id: String = "id",
            title: String = "Compras",
            icon: CategoryIcon = CategoryIcon.ShoppingBag
        ) = Category(
            id = id,
            title = title,
            icon = icon
        )
    }
}

fun CategoryEntity.toCategory() = Category(
    id = id,
    title = title,
    icon = icon
)