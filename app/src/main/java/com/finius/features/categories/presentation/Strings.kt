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

data class CategoriesStrings(
    val homeStrings: CategoriesHomeStrings,
    val formStrings: CategoryFormStrings,
)

val CategoriesStringsPt = CategoriesStrings(
    homeStrings = CategoriesHomeStringsPt,
    formStrings = CategoryFormStringsPt,
)