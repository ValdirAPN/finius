package br.com.finius.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import br.com.finius.domain.model.Colors

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ColorSelector(
    selectedColor: Colors,
    onSelectColor: (Colors) -> Unit,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Colors.entries.forEach { color ->
            val borderColor =
                if (color == selectedColor) MaterialTheme.colorScheme.onBackground else color.value
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(100))
                    .clickable { onSelectColor(color) }
                    .size(32.dp)
                    .background(color.value)
                    .border(2.dp, borderColor, shape = RoundedCornerShape(100))
            )
        }
    }
}