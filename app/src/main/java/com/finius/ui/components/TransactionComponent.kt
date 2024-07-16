package com.finius.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.finius.core.domain.Transaction
import com.finius.core.domain.TransactionType
import com.finius.ui.theme.FiniusTheme

@Composable
fun TransactionComponent(transaction: Transaction, modifier: Modifier = Modifier) {
    with(transaction) {
        val amountString = remember(amount) {
            when (type) {
                TransactionType.INCOME -> "+R$ $amount"
                TransactionType.EXPENSE -> "-R$ $amount"
            }
        }
        Surface(
            color = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        ) {
            Row(
                modifier
                    .padding(8.dp)
                    .fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(100f))
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .size(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = category.icon.iconRes),
                            contentDescription = category.title,
                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(text = counterParty, style = MaterialTheme.typography.labelSmall)
                    }
                }
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = amountString,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(text = "1/10", style = MaterialTheme.typography.labelSmall)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TransactionComponentPreview() {
    FiniusTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            val transaction = Transaction.fakeTransactions().first()
            TransactionComponent(transaction = transaction)
        }
    }
}