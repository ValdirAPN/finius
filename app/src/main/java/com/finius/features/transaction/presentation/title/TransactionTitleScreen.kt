package com.finius.features.transaction.presentation.title

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.finius.R
import com.finius.features.transaction.presentation.category.TransactionCategoryScreen
import com.finius.ui.components.FiniusButton
import com.finius.ui.components.FiniusButtonState
import com.finius.ui.components.FiniusInputField
import com.finius.ui.components.FiniusNavigationBar
import com.finius.ui.components.FiniusNavigationBarLeadingAction
import com.finius.ui.theme.FiniusTheme

class TransactionTitleScreen : Screen {

    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val titleStrings = strings.transactionStrings.titleStrings
        val titleState = rememberTextFieldState()

        TransactionTitleScreenContent(
            strings = titleStrings,
            onClickNavigationIcon = navigator::pop,
            onClickContinue = { navigator.push(TransactionCategoryScreen()) },
            titleState = titleState
        )
    }
}

@Composable
fun TransactionTitleScreenContent(
    strings: TransactionTitleStrings,
    onClickNavigationIcon: () -> Unit,
    onClickContinue: () -> Unit,
    titleState: TextFieldState,
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

                Column(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    FiniusInputField(state = titleState)
                }
            }
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                FiniusButton(
                    text = strings.btnLabel,
                    onClick = onClickContinue,
                    modifier = Modifier.fillMaxWidth(),
                    trailingIconRes = R.drawable.arrow_right_light,
                    state = if (titleState.text.isBlank()) FiniusButtonState.Disabled else FiniusButtonState.Default
                )
            }
        }
    }
}

@Preview
@Composable
private fun TransactionTitleScreenContentPreview() {
    FiniusTheme {

        val titleStrings = strings.transactionStrings.titleStrings

        TransactionTitleScreenContent(
            strings = titleStrings,
            onClickNavigationIcon = {},
            onClickContinue = {},
            titleState = TextFieldState()
        )
    }
}