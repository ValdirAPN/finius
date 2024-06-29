package com.finius.features.transaction.presentation.type

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.finius.core.domain.TransactionType
import com.finius.features.transaction.presentation.counterParty.TransactionCounterPartyScreen
import com.finius.ui.components.FiniusButton
import com.finius.ui.components.FiniusNavigationBar
import com.finius.ui.components.FiniusNavigationBarLeadingAction
import com.finius.ui.components.FiniusRadioButtonGroup
import com.finius.ui.components.FiniusRadioButtonItem
import com.finius.ui.theme.FiniusTheme

class TransactionTypeScreen : Screen {

    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val transactionTypeStrings = strings.transactionStrings.typeStrings

        TransactionTypeScreenContent(
            strings = transactionTypeStrings,
            onClickNavigationIcon = navigator::popUntilRoot,
            onClickContinue = { type ->
                type?.let { navigator.push(TransactionCounterPartyScreen(it)) }
            }
        )
    }
}

@Composable
fun TransactionTypeScreenContent(
    strings: TransactionTypeStrings,
    onClickNavigationIcon: () -> Unit,
    onClickContinue: (TransactionType?) -> Unit,
    modifier: Modifier = Modifier
) {

    val items = remember {
        listOf(
            FiniusRadioButtonItem(
                id = TransactionType.EXPENSE.name,
                label = strings.rbExpenseLabel
            ),
            FiniusRadioButtonItem(
                id = TransactionType.INCOME.name,
                label = strings.rbIncomeLabel
            ),
        )
    }

    var selectedItem by remember {
        mutableStateOf(items.first())
    }

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
                    leadingAction = FiniusNavigationBarLeadingAction.Close(action = onClickNavigationIcon)
                )

                FiniusRadioButtonGroup(
                    items = items,
                    selectedItem = selectedItem,
                    onSelect = { item -> selectedItem = item },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

            }
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                FiniusButton(
                    text = strings.btnLabel,
                    onClick = {
                        val type =
                            TransactionType.entries.firstOrNull { it.name == selectedItem.id }
                        onClickContinue(type)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    trailingIconRes = R.drawable.arrow_right_light
                )
            }
        }
    }
}

@Preview
@Composable
private fun TransactionTypeScreenContentPreview() {
    FiniusTheme {
        val transactionTypeStrings = strings.transactionStrings.typeStrings
        TransactionTypeScreenContent(
            strings = transactionTypeStrings,
            onClickNavigationIcon = {},
            onClickContinue = {}
        )
    }
}