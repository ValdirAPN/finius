package com.finius.features.account.creditCards.presentation.form.limit

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
import com.finius.features.account.bankAccounts.presentation.form.balance.AccountBalanceScreenContent
import com.finius.features.account.creditCards.presentation.form.CardFormScreenModel
import com.finius.features.account.creditCards.presentation.form.dueDay.CardDueDayScreen
import com.finius.ui.components.FiniusButton
import com.finius.ui.components.FiniusButtonState
import com.finius.ui.components.FiniusInputField
import com.finius.ui.components.FiniusNavigationBar
import com.finius.ui.components.FiniusNavigationBarLeadingAction
import com.finius.ui.components.FiniusNumberKeyboard
import com.finius.ui.formatters.MoneyOutputTransformation
import com.finius.ui.theme.FiniusTheme

class CardLimitScreen : Screen {
    @Composable
    override fun Content() {

        val screenModel = rememberScreenModel<CardFormScreenModel>()
        val state by screenModel.uiState.collectAsStateWithLifecycle()

        val navigator = LocalNavigator.currentOrThrow
        val cardLimitStrings = strings.creditCardStrings.formStrings.limitStrings

        CardLimitScreenContent(
            strings = cardLimitStrings,
            limit = state.limit,
            onClickNavigationIcon = { navigator.pop() },
            onClickContinue = { navigator.push(CardDueDayScreen()) }
        )
    }
}

@Composable
fun CardLimitScreenContent(
    strings: CardLimitStrings,
    limit: TextFieldState,
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
                        state = limit,
                        readOnly = true,
                        outputTransformation = MoneyOutputTransformation()
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    FiniusNumberKeyboard(textFieldState = limit)
                    FiniusButton(
                        text = strings.btnLabel,
                        onClick = onClickContinue,
                        trailingIconRes = R.drawable.arrow_right_light,
                        state = if (limit.text.isBlank()) FiniusButtonState.Disabled else FiniusButtonState.Default
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun AccountBalanceScreenContentPreview() {
    FiniusTheme {
        AccountBalanceScreenContent(
            strings = strings.bankAccountStrings.bankAccountFormStrings.bankAccountBalanceStrings,
            balance = TextFieldState(),
            onClickNavigationIcon = {},
            onClickContinue = {}
        )
    }
}