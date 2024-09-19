package br.com.finius.bankAccounts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.finius.R
import br.com.finius.domain.model.PaymentAccount
import br.com.finius.ui.components.Toolbar
import br.com.finius.ui.components.NavigationLeading
import br.com.finius.ui.components.NavigationTrailing
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
object BankAccountsRoute

@Composable
fun BankAccountsScreen(
    onNavigateBack: () -> Unit,
    onNavigateToNewBankAccount: () -> Unit
) {
    val viewModel = koinViewModel<BankAccountsViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getAccounts()
    }

    Content(
        uiState = uiState,
        onNavigateBack = onNavigateBack,
        onNavigateToNewBankAccount = onNavigateToNewBankAccount
    )
}

@Composable
private fun Content(
    uiState: BankAccountsUiState,
    onNavigateBack: () -> Unit,
    onNavigateToNewBankAccount: () -> Unit,
) {
    with(uiState) {
        Scaffold(
            topBar = {
                Toolbar(
                    label = "Contas",
                    leading = NavigationLeading.Close(action = onNavigateBack),
                    primaryAction = NavigationTrailing(
                        iconRes = R.drawable.plus,
                        action = onNavigateToNewBankAccount
                    )
                )
            },
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                items(accounts, key = { account -> account.id }) { account ->
                    BankAccount(account)
                }
            }
        }
    }
}

@Composable
private fun BankAccount(account: PaymentAccount, modifier: Modifier = Modifier) {
    with(account) {
        Row(
            modifier = modifier.padding(horizontal = 24.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(100))
                    .background(color.color)
            )
            Text(
                text = name,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.weight(1f)
            )
            Text(text = balance.toString(), style = MaterialTheme.typography.labelMedium)
        }
    }
}