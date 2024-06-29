package com.finius.features.transaction.presentation.amount

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.finius.R
import com.finius.core.presentation.FiniusSuccessScreen
import com.finius.ui.components.FiniusButton
import com.finius.ui.components.FiniusInputField
import com.finius.ui.components.FiniusNavigationBar
import com.finius.ui.components.FiniusNavigationBarLeadingAction
import com.finius.ui.components.FiniusNumberKeyboard
import com.finius.ui.theme.FiniusTheme

class TransactionAmountScreen : Screen {
    @Composable
    override fun Content() {
        val amountStrings = strings.transactionStrings.amountStrings
        val amountState = rememberTextFieldState()
        val navigator = LocalNavigator.currentOrThrow

        val successScreenTitle = strings.transactionStrings.successScreenTitle

        TransactionAmountScreenContent(
            strings = amountStrings,
            amount = amountState,
            onClickContinue = {
                navigator.push(
                    FiniusSuccessScreen(
                        text = successScreenTitle,
                        onClickContinue = { navigator.popUntilRoot() })
                )
            })
    }
}

@Composable
fun TransactionAmountScreenContent(
    strings: TransactionAmountStrings,
    amount: TextFieldState,
    onClickContinue: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isTotalAmount by remember {
        mutableStateOf(false)
    }

    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                FiniusNavigationBar(
                    title = strings.title,
                    leadingAction = FiniusNavigationBarLeadingAction.Back(action = {})
                )

                Column(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    FiniusInputField(
                        state = amount,
                        readOnly = true,
                        outputTransformation = {
                            if (originalText.isNotEmpty()) {
                                replace(
                                    0,
                                    originalText.length,
                                    (originalText.toString().toDouble() / 100).toString()
                                )
                            }
                        })

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = strings.isTotalAmountLabel, modifier = Modifier.weight(1f))
                        Switch(checked = isTotalAmount, onCheckedChange = { isTotalAmount = it })
                    }
                }
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(16.dp)
                        )
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = strings.installmentValueLabel,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "3x de R$ 461,63",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = strings.totalValueLabel,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "R$ 1.384,90",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                }

                FiniusNumberKeyboard(textFieldState = amount)

                FiniusButton(
                    text = strings.btnLabel,
                    onClick = onClickContinue,
                    modifier = Modifier.fillMaxWidth(),
                    trailingIconRes = R.drawable.arrow_right_light,
                )
            }
        }
    }
}

@Preview
@Composable
private fun TransactionAmountScreenContentPreview() {
    FiniusTheme {
        val amountStrings = strings.transactionStrings.amountStrings
        TransactionAmountScreenContent(
            strings = amountStrings,
            amount = TextFieldState(),
            onClickContinue = {}
        )
    }
}