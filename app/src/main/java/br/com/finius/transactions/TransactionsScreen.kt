package br.com.finius.transactions

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.finius.R
import br.com.finius.domain.model.TransactionType
import br.com.finius.ui.components.NavigationBar
import br.com.finius.ui.components.NavigationLeading
import br.com.finius.ui.components.NavigationTrailing
import br.com.finius.ui.components.TransactionListItem
import kotlinx.serialization.Serializable

@Serializable
object TransactionsRoute

@Composable
fun TransactionsScreen(
    onNavigateBack: () -> Unit,
    onNavigateToNewTransaction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier,
        topBar = {
            NavigationBar(
                label = "Transações",
                leading = NavigationLeading.Close(action = onNavigateBack),
                primaryAction = NavigationTrailing(
                    iconRes = R.drawable.plus,
                    action = onNavigateToNewTransaction
                )
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            repeat(24) {
                item {
                    TransactionListItem(
                        name = "Salário",
                        amount = 4500.0,
                        type = TransactionType.Income
                    )
                }
            }
        }
    }
}