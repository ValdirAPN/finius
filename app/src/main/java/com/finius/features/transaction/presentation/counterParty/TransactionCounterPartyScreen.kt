package com.finius.features.transaction.presentation.counterParty

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.finius.R
import com.finius.core.domain.TransactionType
import com.finius.features.transaction.presentation.TransactionFormScreenModel
import com.finius.features.transaction.presentation.account.TransactionAccountScreen
import com.finius.ui.components.FiniusButton
import com.finius.ui.components.FiniusButtonState
import com.finius.ui.components.FiniusInputField
import com.finius.ui.components.FiniusNavigationBar
import com.finius.ui.components.FiniusNavigationBarLeadingAction
import com.finius.ui.theme.FiniusTheme

class TransactionCounterPartyScreen : Screen {

    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {

        val model = rememberScreenModel<TransactionFormScreenModel>()
        val state by model.uiState.collectAsStateWithLifecycle()

        val navigator = LocalNavigator.currentOrThrow
        val counterPartyStrings = strings.transactionStrings.counterPartyStrings

        TransactionCounterPartyScreenContent(
            strings = counterPartyStrings,
            transactionType = state.type,
            onClickNavigationIcon = navigator::pop,
            onClickContinue = { navigator.push(TransactionAccountScreen()) },
            counterPartyState = state.counterParty
        )
    }
}

@Composable
fun TransactionCounterPartyScreenContent(
    strings: TransactionCounterPartyStrings,
    transactionType: TransactionType,
    onClickNavigationIcon: () -> Unit,
    onClickContinue: () -> Unit,
    counterPartyState: TextFieldState,
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
                    title = strings.title(transactionType),
                    subtitle = strings.subtitle(transactionType),
                    leadingAction = FiniusNavigationBarLeadingAction.Back(action = onClickNavigationIcon)
                )

                Column(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    FiniusInputField(state = counterPartyState)
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
                    state = if (counterPartyState.text.isBlank()) FiniusButtonState.Disabled else FiniusButtonState.Default
                )
            }
        }
    }
}

@Preview
@Composable
private fun TransactionCounterPartyScreenContentIncomePreview() {
    FiniusTheme {
        val counterPartyStrings = strings.transactionStrings.counterPartyStrings
        TransactionCounterPartyScreenContent(
            strings = counterPartyStrings,
            transactionType = TransactionType.INCOME,
            onClickNavigationIcon = {},
            onClickContinue = {},
            counterPartyState = TextFieldState()
        )
    }
}

@Preview
@Composable
private fun TransactionCounterPartyScreenContentExpensePreview() {
    FiniusTheme {
        val counterPartyStrings = strings.transactionStrings.counterPartyStrings
        TransactionCounterPartyScreenContent(
            strings = counterPartyStrings,
            transactionType = TransactionType.EXPENSE,
            onClickNavigationIcon = {},
            onClickContinue = {},
            counterPartyState = TextFieldState()
        )
    }
}