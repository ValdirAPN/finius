package com.finius.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.finius.R
import com.finius.ui.theme.FiniusTheme

enum class FiniusListItemState(
    val color: @Composable () -> Color,
) {
    DEFAULT(
        color = { Color.Unspecified }
    ),
    SELECTED(
        color = { MaterialTheme.colorScheme.primaryContainer }
    )
}

@Composable
fun FiniusListItem(
    label: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    onClick: (() -> Unit)? = null,
    state: FiniusListItemState = FiniusListItemState.DEFAULT,
    leadingContent: @Composable ColumnScope.() -> Unit = {},
    trailingContent: @Composable ColumnScope.() -> Unit = {}
) {
    Surface(
        onClick = { onClick?.invoke() },
        enabled = onClick != null,
        modifier = modifier
            .clip(
                RoundedCornerShape(8.dp)
            ),
        color = state.color()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column {
                leadingContent()
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = label, style = MaterialTheme.typography.bodyMedium)
                description?.let {
                    Text(text = it, style = MaterialTheme.typography.bodySmall)
                }
            }

            Column {
                trailingContent()
            }
        }
    }
}

@Preview
@Composable
private fun FiniusListItemPreview() {
    FiniusTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column {
                FiniusListItem(
                    label = "Label",
                    description = "Some description",
                    leadingContent = {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(100))
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.wallet_fill),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primaryContainer
                            )
                        }
                    },
                    trailingContent = {
                        Text(text = "Value", style = MaterialTheme.typography.bodyMedium)
                    }
                )

                FiniusListItem(
                    label = "Label",
                    description = "Some description",
                    state = FiniusListItemState.SELECTED,
                    leadingContent = {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(100))
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.wallet_fill),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primaryContainer
                            )
                        }
                    },
                    trailingContent = {
                        Text(text = "Value", style = MaterialTheme.typography.bodyMedium)
                    }
                )
            }
        }
    }
}