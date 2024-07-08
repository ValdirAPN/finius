package com.finius.features.account.creditCards.presentation.form.name

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
import com.finius.features.account.creditCards.presentation.form.CardFormScreenModel
import com.finius.features.account.creditCards.presentation.form.brand.CardBrandScreen
import com.finius.ui.components.FiniusButton
import com.finius.ui.components.FiniusButtonState
import com.finius.ui.components.FiniusInputField
import com.finius.ui.components.FiniusNavigationBar
import com.finius.ui.components.FiniusNavigationBarLeadingAction
import com.finius.ui.theme.FiniusTheme

class CardNameScreen : Screen {
    @Composable
    override fun Content() {

        val screenModel = rememberScreenModel<CardFormScreenModel>()
        val state by screenModel.uiState.collectAsStateWithLifecycle()

        val navigator = LocalNavigator.currentOrThrow

        val cardNameStrings = strings.creditCardStrings.formStrings.nameStrings

        CardNameScreenContent(
            strings = cardNameStrings,
            name = state.name,
            onClickNavigationIcon = { navigator.pop() },
            onClickContinue = { navigator.push(CardBrandScreen()) }
        )
    }
}

@Composable
fun CardNameScreenContent(
    strings: CardNameStrings,
    name: TextFieldState,
    onClickNavigationIcon: () -> Unit,
    onClickContinue: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier, color = MaterialTheme.colorScheme.background) {
        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            FiniusNavigationBar(
                title = strings.title,
                leadingAction = FiniusNavigationBarLeadingAction.Back(action = onClickNavigationIcon)
            )

            Column(modifier = Modifier.weight(1f)) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    FiniusInputField(state = name)
                }
                Column(modifier = Modifier.padding(16.dp)) {
                    FiniusButton(
                        text = strings.btnLabel,
                        onClick = onClickContinue,
                        trailingIconRes = R.drawable.arrow_right_light,
                        state = if (name.text.isBlank()) FiniusButtonState.Disabled else FiniusButtonState.Default
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CardNameScreenContentPreview() {
    FiniusTheme {
        CardNameScreenContent(
            strings = strings.creditCardStrings.formStrings.nameStrings,
            name = TextFieldState(),
            onClickNavigationIcon = {},
            onClickContinue = {}
        )
    }
}