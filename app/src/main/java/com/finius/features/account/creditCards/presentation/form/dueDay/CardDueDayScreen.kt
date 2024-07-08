package com.finius.features.account.creditCards.presentation.form.dueDay

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.finius.R
import com.finius.core.presentation.FiniusSuccessScreen
import com.finius.features.account.creditCards.presentation.form.CardFormScreenModel
import com.finius.ui.components.FiniusButton
import com.finius.ui.components.FiniusButtonState
import com.finius.ui.components.FiniusInputField
import com.finius.ui.components.FiniusNavigationBar
import com.finius.ui.components.FiniusNavigationBarLeadingAction
import com.finius.ui.components.FiniusNumberKeyboard
import com.finius.ui.theme.FiniusTheme

class CardDueDayScreen : Screen {
    @Composable
    override fun Content() {

        val screenModel = rememberScreenModel<CardFormScreenModel>()
        val state by screenModel.uiState.collectAsStateWithLifecycle()

        val navigator = LocalNavigator.currentOrThrow
        val cardDueDayStrings = strings.creditCardStrings.formStrings.dueDayStrings
        val successTitle = strings.creditCardStrings.successTitle

        CardDueDayScreenContent(
            strings = cardDueDayStrings,
            dueDay = state.dueDay,
            onClickNavigationIcon = { navigator.pop() },
            onClickContinue = {
                screenModel.createAccount()
                navigator.push(
                    FiniusSuccessScreen(
                        text = successTitle,
                        onClickContinue = { navigator.popUntilRoot() }
                    )
                )
            }
        )
    }
}

@Composable
fun CardDueDayScreenContent(
    strings: CardDueDayStrings,
    dueDay: TextFieldState,
    onClickNavigationIcon: () -> Unit,
    onClickContinue: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(color = MaterialTheme.colorScheme.background, modifier = modifier) {
        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            FiniusNavigationBar(
                title = strings.title,
                leadingAction = FiniusNavigationBarLeadingAction.Back(action = onClickNavigationIcon)
            )

            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    FiniusInputField(
                        state = dueDay
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    FiniusNumberKeyboard(textFieldState = dueDay)
                    FiniusButton(
                        text = strings.btnLabel,
                        onClick = onClickContinue,
                        trailingIconRes = R.drawable.check_light,
                        state = if (dueDay.text.isBlank()) FiniusButtonState.Disabled else FiniusButtonState.Default
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CardDueDayScreenContentPreview() {
    FiniusTheme {
        CardDueDayScreenContent(
            strings = strings.creditCardStrings.formStrings.dueDayStrings,
            dueDay = TextFieldState(),
            onClickNavigationIcon = {},
            onClickContinue = {}
        )
    }
}