package com.finius.features.transaction.presentation.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.finius.R
import com.finius.core.domain.Category
import com.finius.features.transaction.presentation.TransactionFormScreenModel
import com.finius.features.transaction.presentation.date.TransactionDateScreen
import com.finius.ui.components.FiniusButton
import com.finius.ui.components.FiniusButtonState
import com.finius.ui.components.FiniusDivider
import com.finius.ui.components.FiniusListItem
import com.finius.ui.components.FiniusListItemState
import com.finius.ui.components.FiniusNavigationBar
import com.finius.ui.components.FiniusNavigationBarLeadingAction

class TransactionCategoryScreen : Screen {
    @Composable
    override fun Content() {

        val model = rememberScreenModel<TransactionCategoryScreenModel>()
        val state by model.uiState.collectAsStateWithLifecycle()

        val formModel = rememberScreenModel<TransactionFormScreenModel>()
        val formState by formModel.uiState.collectAsStateWithLifecycle()

        val navigator = LocalNavigator.currentOrThrow
        val categoryStrings = strings.transactionStrings.categoryStrings

        TransactionCategoryScreenContent(
            strings = categoryStrings,
            categories = state.categories,
            selectedCategory = formState.category,
            onSelectCategory = formModel::setCategory,
            onClickNavigationIcon = navigator::pop,
            onClickContinue = { navigator.push(TransactionDateScreen()) }
        )
    }
}

@Composable
fun TransactionCategoryScreenContent(
    strings: TransactionCategoryStrings,
    selectedCategory: Category?,
    categories: List<Category>,
    onSelectCategory: (Category) -> Unit,
    onClickNavigationIcon: () -> Unit,
    onClickContinue: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            Column(
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                FiniusNavigationBar(
                    title = strings.title,
                    leadingAction = FiniusNavigationBarLeadingAction.Back(action = onClickNavigationIcon)
                )

                CategoriesContainer(
                    categories = categories,
                    selectedCategory = selectedCategory,
                    onClickCategory = onSelectCategory,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                FiniusButton(
                    text = strings.btnLabel,
                    onClick = onClickContinue,
                    modifier = Modifier.fillMaxWidth(),
                    trailingIconRes = R.drawable.arrow_right_light,
                    state = if (selectedCategory == null) FiniusButtonState.Disabled else FiniusButtonState.Default
                )
            }
        }
    }
}

@Composable
fun CategoriesContainer(
    categories: List<Category>,
    selectedCategory: Category?,
    onClickCategory: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(categories, key = { category -> category.id }) { category ->
            FiniusListItem(
                label = category.title,
                onClick = { onClickCategory(category) },
                state = if (category.id == selectedCategory?.id) FiniusListItemState.Selected else FiniusListItemState.Default,
                leadingContent = {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(100))
                            .background(MaterialTheme.colorScheme.onBackground)
                            .padding(4.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = category.icon.iconRes),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primaryContainer
                        )
                    }
                }
            )

            if (category != categories.last()) {
                FiniusDivider(
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                )
            }
        }
    }
}