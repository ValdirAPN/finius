package com.finius.features.categories.presentation

data class CategoryFormStrings(
    val title: String,
    val btnLabel: String,
    val titleLabel: String,
    val iconLabel: String,
    val successText: String,
)

val CategoryFormStringsPt = CategoryFormStrings(
    title = "Criar nova categoria",
    btnLabel = "Confirmar",
    titleLabel = "Título",
    iconLabel = "Ícone",
    successText = "Categoria criada com sucesso!",
)
data class CategoriesHomeStrings(
    val title: String,
    val btnLabel: String,
)

val CategoriesHomeStringsPt = CategoriesHomeStrings(
    title = "Categorias",
    btnLabel = "Adicionar nova categoria",
)

data class DefaultCategoriesStrings(
    val shopping: String,
    val food: String,
    val travel: String,
    val transport: String,
    val gift: String,
    val salary: String,
)

val DefaultCategoriesStringsPt = DefaultCategoriesStrings(
    shopping = "Compras",
    food = "Alimetação",
    travel = "Viagem",
    transport = "Transporte",
    gift = "Presente",
    salary = "Salário",
)

data class CategoriesStrings(
    val homeStrings: CategoriesHomeStrings,
    val formStrings: CategoryFormStrings,
    val defaultCategories: DefaultCategoriesStrings,
)

val CategoriesStringsPt = CategoriesStrings(
    homeStrings = CategoriesHomeStringsPt,
    formStrings = CategoryFormStringsPt,
    defaultCategories = DefaultCategoriesStringsPt
)