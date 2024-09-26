package br.com.finius.features.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.finius.R
import br.com.finius.domain.model.PaymentAccount
import br.com.finius.ui.components.Toolbar
import br.com.finius.ui.components.NavigationLeading
import br.com.finius.ui.components.NavigationTrailing
import br.com.finius.ui.theme.Melon
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
object CardsRoute

@Composable
fun CardsScreen(
    onNavigateBack: () -> Unit,
    onNavigateToNewCard: () -> Unit,
) {

    val viewModel = koinViewModel<CardsViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getAccounts()
    }

    Content(
        uiState = uiState,
        onNavigateBack = onNavigateBack,
        onNavigateToNewCard = onNavigateToNewCard
    )
}

@Composable
private fun Content(
    uiState: CardsUiState,
    onNavigateBack: () -> Unit,
    onNavigateToNewCard: () -> Unit,
) {
    with(uiState) {
        Scaffold(
            topBar = {
                Toolbar(
                    label = "Cartões",
                    leading = NavigationLeading.Close(action = onNavigateBack),
                    primaryAction = NavigationTrailing(
                        iconRes = R.drawable.plus,
                        action = onNavigateToNewCard
                    )
                )
            },
        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                if (cards.isEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.notfound),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }

                        Text(
                            text = "Não tem nada por aqui. Clique em + para cadastrar um novo cartão.",
                            style = MaterialTheme.typography.bodySmall,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 48.dp),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                } else {
                    LazyColumn {
                        items(cards, key = { card -> card.id }) { card ->
                            CreditCard(card)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CreditCard(
    card: PaymentAccount,
    modifier: Modifier = Modifier
) {
    with(card) {
        Row(
            modifier = modifier.padding(horizontal = 24.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(100))
                    .background(color.value)
            )
            Text(
                text = name,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.weight(1f)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(text = balance.format(), style = MaterialTheme.typography.labelMedium)
                Text(
                    text = "de ${balance.format()}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}