package br.com.finius.cards

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
object CardsRoute

@Composable
fun CardsScreen(
    onNavigateBack: () -> Unit,
    onNavigateToNewCard: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier,
        topBar = {
            NavigationBar(
                label = "Cartões",
                leading = NavigationLeading.Close(action = onNavigateBack),
                primaryAction = NavigationTrailing(
                    iconRes = R.drawable.plus,
                    action = onNavigateToNewCard
                )
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            CreditCard(label = "BB Ouro Card", availableLimit = 6123.71, totalLimit = 9999.0)
            CreditCard(label = "Itaú Click", availableLimit = 2913.91, totalLimit = 12100.0)
        }
    }
}

@Composable
private fun CreditCard(
    label: String,
    availableLimit: Double,
    totalLimit: Double,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(horizontal = 24.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(100))
                .background(Melon)
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.weight(1f)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(text = availableLimit.toString(), style = MaterialTheme.typography.labelMedium)
            Text(
                text = "de $totalLimit",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}