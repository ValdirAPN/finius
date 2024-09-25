package br.com.finius.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.finius.ui.theme.FiniusTheme
import br.com.finius.ui.theme.Onyx

@Composable
fun <T> HorizontalSelector(
    vararg items: T,
    selectedItem: T,
    onSelectItem: (item: T) -> Unit,
    itemToString: (T) -> String,
    modifier: Modifier = Modifier
) {
    Row(modifier.padding(horizontal = 24.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items.forEach { item ->
            val backgroundColor = if (selectedItem == item) Onyx else Color.White
            val contentColor = if (selectedItem == item) Color.White else Onyx
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .height(48.dp)
                    .weight(1f)
                    .clickable { onSelectItem(item) }
                    .background(backgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = itemToString(item),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = contentColor
                )
            }
        }
    }
}

@Preview
@Composable
private fun HorizontalSelectorPreview() {
    FiniusTheme {
        HorizontalSelector("Despesa", "Receita", selectedItem = "Despesa", itemToString = {""}, onSelectItem = {})
    }
}