package br.com.finius.bankAccounts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import br.com.finius.R
import br.com.finius.ui.components.NavigationBar
import br.com.finius.ui.components.NavigationLeading
import br.com.finius.ui.components.NavigationTrailing
import br.com.finius.ui.theme.Melon
import kotlinx.serialization.Serializable

@Serializable
object BankAccountsRoute

@Composable
fun BankAccountsScreen(
    onNavigateBack: () -> Unit,
    onNavigateToNewBankAccount: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier,
        topBar = {
            NavigationBar(
                label = "Contas",
                leading = NavigationLeading.Close(action = onNavigateBack),
                primaryAction = NavigationTrailing(
                    iconRes = R.drawable.plus,
                    action = onNavigateToNewBankAccount
                )
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            BankAccount(label = "Banco do Brasil", balance = 1239.23)
            BankAccount(label = "Itaú", balance = 2680.25)
        }
    }
}

@Composable
private fun BankAccount(label: String, balance: Double, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(horizontal = 24.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(100))
            .background(Melon))
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.weight(1f)
        )
        Text(text = balance.toString(), style = MaterialTheme.typography.labelMedium)
    }
}