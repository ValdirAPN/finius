package com.finius.features.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.finius.R
import com.finius.core.domain.Transaction
import com.finius.features.bankAccounts.presentation.home.AccountsHomeScreen
import com.finius.features.transaction.presentation.type.TransactionTypeScreen
import com.finius.ui.components.FiniusShortcutButton
import com.finius.ui.components.TransactionComponent
import com.finius.ui.theme.FiniusTheme

object HomeScreen : Screen {

    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val homeStrings = strings.homeStrings
        val transactions = Transaction.fakeTransactions()

        HomeScreenContent(
            strings = homeStrings,
            transactions = transactions,
            onClickNewTransaction = { navigator.push(TransactionTypeScreen()) },
            onClickAccounts = { navigator.push(AccountsHomeScreen()) }
        )
    }
}

@Composable
fun HomeScreenContent(
    strings: HomeStrings,
    transactions: List<Transaction>,
    modifier: Modifier = Modifier,
    onClickNewTransaction: () -> Unit,
    onClickAccounts: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        HomeScreenHeader(
            strings = strings,
            onClickNewTransaction = onClickNewTransaction,
            onClickAccounts = onClickAccounts
        )
        HomeScreenTransactions(transactions = transactions)
    }
}

@Preview
@Composable
private fun HomeScreenContentPreview() {
    FiniusTheme {

        val transactions = Transaction.fakeTransactions()
        val homeStrings = strings.homeStrings

        HomeScreenContent(
            strings = homeStrings,
            transactions = transactions,
            onClickNewTransaction = {},
            onClickAccounts = {}
        )
    }
}

@Composable
fun HomeScreenHeader(
    strings: HomeStrings,
    onClickNewTransaction: () -> Unit,
    onClickAccounts: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        Column(
            modifier = Modifier.padding(vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 32.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = strings.balance, style = MaterialTheme.typography.labelSmall)
                Text(text = "R$ 1.827,00", style = MaterialTheme.typography.headlineSmall)
            }

            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .horizontalScroll(
                        rememberScrollState()
                    )
            ) {
                FiniusShortcutButton(
                    icon = painterResource(id = R.drawable.plus_light),
                    title = strings.newTransactionLabel,
                    onClick = onClickNewTransaction
                )

                FiniusShortcutButton(
                    icon = painterResource(id = R.drawable.piggy_light),
                    title = strings.accountsLabel,
                    onClick = onClickAccounts
                )

                FiniusShortcutButton(
                    icon = painterResource(id = R.drawable.credit_card_light),
                    title = strings.cardsLabel,
                    onClick = {}
                )

                FiniusShortcutButton(
                    icon = painterResource(id = R.drawable.chart_donut_light),
                    title = strings.transactionsOverviewLabel,
                    onClick = {}
                )
            }
        }
    }
}

@Composable
fun HomeScreenTransactions(transactions: List<Transaction>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(items = transactions, key = { item -> item.id }) { transaction ->
            TransactionComponent(
                transaction = transaction
            )
        }
    }
}