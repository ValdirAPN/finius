package com.finius.features.categories.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.finius.R
import com.finius.core.domain.CategoryIcon
import com.finius.core.presentation.FiniusSuccessScreen
import com.finius.ui.components.FiniusButton
import com.finius.ui.components.FiniusInputField
import com.finius.ui.components.FiniusNavigationBar
import com.finius.ui.components.FiniusNavigationBarLeadingAction
import com.finius.ui.theme.FiniusTheme

class CategoryFormScreen : Screen {
    @Composable
    override fun Content() {

        val model = rememberScreenModel<CategoryFormScreenModel>()
        val state by model.uiState.collectAsStateWithLifecycle()

        val navigator = LocalNavigator.currentOrThrow

        val categoryFormStrings = strings.categoriesStrings.formStrings

        CategoryFormScreenContent(
            strings = categoryFormStrings,
            title = state.title,
            selectedIcon = state.selectedIcon,
            onClickIcon = model::setIcon,
            onClickConfirm = {
                model.createCategory()
                navigator.replace(
                    FiniusSuccessScreen(
                        text = categoryFormStrings.successText,
                        onClickContinue = navigator::popUntilRoot
                    )
                )
            },
            onClickNavigationIcon = {}
        )
    }
}

@Composable
fun CategoryFormScreenContent(
    strings: CategoryFormStrings,
    title: TextFieldState,
    selectedIcon: CategoryIcon?,
    onClickIcon: (icon: CategoryIcon) -> Unit,
    onClickConfirm: () -> Unit,
    onClickNavigationIcon: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            FiniusNavigationBar(
                title = strings.title,
                leadingAction = FiniusNavigationBarLeadingAction.Back(
                    action = onClickNavigationIcon
                )
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                FiniusInputField(state = title, label = strings.titleLabel)

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = strings.iconLabel,
                        style = MaterialTheme.typography.labelMedium
                    )

                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = 64.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(CategoryIcon.entries) { icon ->
                            val (background, foreground) = if (selectedIcon == icon) {
                                MaterialTheme.colorScheme.primaryContainer to MaterialTheme.colorScheme.onPrimaryContainer
                            } else {
                                MaterialTheme.colorScheme.onBackground to MaterialTheme.colorScheme.primaryContainer
                            }

                            Box(
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(RoundedCornerShape(100))
                                    .clickable { onClickIcon(icon) }
                                    .background(background),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = icon.iconRes),
                                    contentDescription = icon.name,
                                    tint = foreground
                                )
                            }
                        }
                    }
                }

            }

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                FiniusButton(
                    text = strings.btnLabel,
                    onClick = onClickConfirm,
                    trailingIconRes = R.drawable.check_light
                )
            }
        }
    }
}

@Preview
@Composable
private fun CategoryFormScreenContentPreview() {
    FiniusTheme {
        val categoryFormStrings = strings.categoriesStrings.formStrings
        CategoryFormScreenContent(
            strings = categoryFormStrings,
            title = rememberTextFieldState(),
            selectedIcon = CategoryIcon.Gift,
            onClickIcon = {},
            onClickConfirm = {},
            onClickNavigationIcon = {}
        )
    }
}