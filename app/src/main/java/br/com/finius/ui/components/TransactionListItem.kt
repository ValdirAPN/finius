package br.com.finius.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.finius.R
import br.com.finius.domain.model.Transaction
import br.com.finius.domain.model.TransactionType
import br.com.finius.ui.theme.FrenchGray
import br.com.finius.ui.theme.LightGreen2
import br.com.finius.ui.theme.Melon
import br.com.finius.ui.theme.Viridian

@Composable
fun TransactionListItem(transaction: Transaction, modifier: Modifier = Modifier) {
    with(transaction) {
        Row(
            modifier = modifier.padding(horizontal = 24.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(
                        RoundedCornerShape(100)
                    )
                    .background(Melon),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.currency_dollar_simple),
                    contentDescription = null
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
                )
                Text(
                    text = "15 de Setembro de 2024",
                    style = MaterialTheme.typography.bodySmall,
                    color = FrenchGray
                )
            }

            val amountColor = when (type) {
                TransactionType.Income -> Viridian
                TransactionType.Expense -> MaterialTheme.colorScheme.onBackground
            }

            Text(
                text = "R$ $amount",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                color = amountColor
            )
        }
    }
}