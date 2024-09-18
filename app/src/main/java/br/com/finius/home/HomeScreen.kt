package br.com.finius.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.finius.R
import br.com.finius.ui.components.TransactionListItem
import br.com.finius.ui.theme.FiniusTheme
import br.com.finius.ui.theme.MintCream
import br.com.finius.ui.theme.Viridian
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
object HomeRoute

@Composable
fun HomeScreen(
    onNavigateToNewTransaction: () -> Unit,
    onNavigateToBankAccounts: () -> Unit,
    onNavigateToCards: () -> Unit,
    onNavigateToTransactions: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val viewModel = koinViewModel<HomeViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getTransactions()
    }

    Scaffold(modifier) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(id = R.drawable.finius), contentDescription = null)
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Box(
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(100)
                            )
                            .padding(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.eye),
                            contentDescription = null
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(100)
                            )
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.user),
                            contentDescription = null
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .padding(
                        horizontal = 24.dp,
                        vertical = 48.dp
                    ),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "R$ 3.919,48", fontSize = 24.sp)
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(100))
                        .background(MintCream)
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_circle_up_right),
                        contentDescription = null,
                        tint = Viridian
                    )
                    Text(
                        text = "3,92%",
                        style = MaterialTheme.typography.bodySmall,
                        color = Viridian
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row {
                    DashboardButton(
                        label = "Nova transação",
                        iconRes = R.drawable.currency_circle_dollar,
                        onClick = onNavigateToNewTransaction
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    DashboardButton(
                        label = "Contas",
                        iconRes = R.drawable.bank,
                        onClick = onNavigateToBankAccounts
                    )
                    DashboardButton(
                        label = "Cartões",
                        iconRes = R.drawable.credit_card,
                        onClick = onNavigateToCards
                    )
                    DashboardButton(
                        label = "Transações",
                        iconRes = R.drawable.chart_donut,
                        onClick = onNavigateToTransactions
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    DashboardButton(
                        label = "Metas",
                        iconRes = R.drawable.target,
                        onClick = {}
                    )
                    DashboardButton(
                        label = "Assinaturas",
                        iconRes = R.drawable.arrows_counter_clock_wise,
                        onClick = {}
                    )
                }
            }

            Column(
                modifier = Modifier.padding(vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Últimas transações",
                    modifier = Modifier.padding(horizontal = 24.dp),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
                )
                Column {
                    uiState.lastTransactions.forEach { transaction ->
                        TransactionListItem(
                            name = transaction.name,
                            amount = transaction.amount,
                            type = transaction.type
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RowScope.DashboardButton(
    label: String,
    iconRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .weight(1f)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
            .height(96.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Icon(painter = painterResource(id = iconRes), contentDescription = label)
        Spacer(modifier = Modifier.size(12.dp))
        Text(text = label, style = MaterialTheme.typography.labelSmall)
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    FiniusTheme {
        HomeScreen(
            onNavigateToNewTransaction = {},
            onNavigateToBankAccounts = {},
            onNavigateToCards = {},
            onNavigateToTransactions = {}
        )
    }
}