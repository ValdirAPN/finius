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

enum class Colors(val color: Color) {
    Melon(Color(0xFFFFABAB)),
    Apricot(Color(0xFFFFC7AB)),
    PeachYellow(Color(0xFFFFE3AB)),
    Mindaro(Color(0xFFFFFFAB)),
    LightGreen(Color(0xFFC7FFAB)),
    LightGreen2(Color(0xFFABFFAB)),
    Aquamarine(Color(0xFFABFFC7)),
    Aquamarine2(Color(0xFFABFFE3)),
    Celeste(Color(0xFFABFFFF)),
    UranianBlue(Color(0xFFABE3FF)),
    JordyBlue(Color(0xFFABC7FF)),
    PowderBlue(Color(0xFFABABFF)),
    Mauve(Color(0xFFC7ABFF)),
    Mauve2(Color(0xFFE3ABFF)),
    Mauve3(Color(0xFFFFABFF)),
    LavenderPink(Color(0xFFFFABE3)),
    CarnationPink(Color(0xFFFFABC7))
}

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