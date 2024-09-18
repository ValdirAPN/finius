package br.com.finius.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.finius.domain.model.Colors

@Composable
fun ColorSelector(
    selectedColor: Colors,
    onSelectColor: (Colors) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.FixedSize(32.dp),
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(Colors.entries) { color ->
            val borderColor =
                if (color == selectedColor) MaterialTheme.colorScheme.onBackground else color.color
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(100))
                    .clickable { onSelectColor(color) }
                    .size(32.dp)
                    .background(color.color)
                    .border(2.dp, borderColor, shape = RoundedCornerShape(100))
            )
        }
    }
}