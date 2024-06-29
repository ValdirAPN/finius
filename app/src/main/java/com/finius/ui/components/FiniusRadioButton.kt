package com.finius.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.finius.ui.theme.FiniusTheme

enum class FiniusRadioButtonState(
    val backgroundColor: @Composable () -> Color,
    val radioBorder: @Composable () -> Pair<Dp, Color>
) {
    DEFAULT(
        backgroundColor = { Color.Unspecified },
        radioBorder = { 1.dp to MaterialTheme.colorScheme.onBackground }
    ),
    SELECTED(
        backgroundColor = { MaterialTheme.colorScheme.primaryContainer },
        radioBorder = { 3.dp to MaterialTheme.colorScheme.primary }
    )
}

@Composable
fun FiniusRadioButton(
    label: String,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
    state: FiniusRadioButtonState = FiniusRadioButtonState.DEFAULT
) {

    val (radioBorderWidth, radioBorderColor) = state.radioBorder()
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(
                RoundedCornerShape(8.dp)
            )
            .clickable { onSelect() }
            .background(state.backgroundColor())
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(100))
                .size(12.dp)
                .border(
                    radioBorderWidth,
                    radioBorderColor,
                    shape = RoundedCornerShape(100)
                )
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview
@Composable
private fun FiniusRadioButtonPreview() {
    FiniusTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column(modifier = Modifier.padding(16.dp)) {
                FiniusRadioButton(
                    label = "Receita",
                    modifier = Modifier.fillMaxWidth(),
                    onSelect = {})
                FiniusRadioButton(
                    label = "Receita",
                    modifier = Modifier.fillMaxWidth(),
                    state = FiniusRadioButtonState.SELECTED,
                    onSelect = {}
                )
            }
        }
    }
}

data class FiniusRadioButtonItem(
    val id: String,
    val label: String,
)

@Composable
fun FiniusRadioButtonGroup(
    items: List<FiniusRadioButtonItem>,
    selectedItem: FiniusRadioButtonItem,
    onSelect: (FiniusRadioButtonItem) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = items, key = { item -> item.id }) { item ->
            val state = if (item.id == selectedItem.id) {
                FiniusRadioButtonState.SELECTED
            } else FiniusRadioButtonState.DEFAULT
            FiniusRadioButton(label = item.label, onSelect = { onSelect(item) }, state = state)
        }
    }
}

@Preview
@Composable
private fun FiniusRadioButtonGroupPreview() {
    FiniusTheme {
        Surface(color = MaterialTheme.colorScheme.background) {

            val radioButtonItems = remember {
                listOf(
                    FiniusRadioButtonItem(id = "1", "Receita"),
                    FiniusRadioButtonItem(id = "2", "Despesa"),
                )
            }

            var selectedItem by remember {
                mutableStateOf(radioButtonItems.first())
            }

            FiniusRadioButtonGroup(
                items = radioButtonItems,
                onSelect = { item ->
                    selectedItem = item
                },
                modifier = Modifier.padding(16.dp),
                selectedItem = selectedItem
            )
        }
    }
}