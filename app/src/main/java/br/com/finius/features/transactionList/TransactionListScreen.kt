package br.com.finius.features.transactionList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.finius.R
import br.com.finius.ui.components.Toolbar
import br.com.finius.ui.components.NavigationLeading
import br.com.finius.ui.components.NavigationTrailing
import br.com.finius.ui.components.NoTransactionsFoundComponent
import br.com.finius.ui.components.TransactionListItem
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
object TransactionListRoute

@Composable
fun TransactionListScreen(
    onNavigateBack: () -> Unit,
    onNavigateToNewTransaction: () -> Unit
) {

    val viewModel = koinViewModel<TransactionListViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getTransactions()
    }

    Content(
        uiState = uiState,
        onNavigateBack = onNavigateBack,
        onNavigateToNewTransaction = onNavigateToNewTransaction,
    )
}

@Composable
private fun Content(
    uiState: TransactionListUiState,
    onNavigateBack: () -> Unit,
    onNavigateToNewTransaction: () -> Unit,
) {
    with(uiState) {
        Scaffold(
            topBar = {
                Toolbar(
                    label = "Transações",
                    leading = NavigationLeading.Close(action = onNavigateBack),
                    primaryAction = NavigationTrailing(
                        iconRes = R.drawable.plus,
                        action = onNavigateToNewTransaction
                    ),
                )
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                if (transactions.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        NoTransactionsFoundComponent()
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {

                        items(transactions) { transaction ->
                            TransactionListItem(transaction = transaction)
                        }
                    }
                }
            }
        }
    }
}