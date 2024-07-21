package com.finius.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.finius.R
import com.finius.ui.theme.FiniusTheme

@Composable
fun FiniusNumberKeyboard(
    textFieldState: TextFieldState,
    modifier: Modifier = Modifier,
    condition: (value: String) -> Boolean = { true }
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(316.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column {
            val rowModifier = Modifier.weight(1f)
            Row(modifier = rowModifier, verticalAlignment = Alignment.CenterVertically) {
                FiniusKeyboardButton(
                    content = { Text(text = "1") },
                    onClick = {
                        val input = "1"
                        if (condition(input)) {
                            textFieldState.edit {
                                append(input)
                            }
                        }
                    }
                )
                FiniusKeyboardButton(
                    content = { Text(text = "2") },
                    onClick = {
                        val input = "2"
                        if (condition(input)) {
                            textFieldState.edit {
                                append(input)
                            }
                        }
                    }
                )
                FiniusKeyboardButton(
                    content = { Text(text = "3") },
                    onClick = {
                        val input = "3"
                        if (condition(input)) {
                            textFieldState.edit {
                                append(input)
                            }
                        }
                    }
                )
            }
            Row(modifier = rowModifier, verticalAlignment = Alignment.CenterVertically) {
                FiniusKeyboardButton(
                    content = { Text(text = "4") },
                    onClick = {
                        val input = "4"
                        if (condition(input)) {
                            textFieldState.edit {
                                append(input)
                            }
                        }
                    }
                )
                FiniusKeyboardButton(
                    content = { Text(text = "5") },
                    onClick = {
                        val input = "5"
                        if (condition(input)) {
                            textFieldState.edit {
                                append(input)
                            }
                        }
                    }
                )
                FiniusKeyboardButton(
                    content = { Text(text = "6") },
                    onClick = {
                        val input = "6"
                        if (condition(input)) {
                            textFieldState.edit {
                                append(input)
                            }
                        }
                    }
                )
            }
            Row(modifier = rowModifier, verticalAlignment = Alignment.CenterVertically) {
                FiniusKeyboardButton(
                    content = { Text(text = "7") },
                    onClick = {
                        val input = "7"
                        if (condition(input)) {
                            textFieldState.edit {
                                append(input)
                            }
                        }
                    }
                )
                FiniusKeyboardButton(
                    content = { Text(text = "8") },
                    onClick = {
                        val input = "8"
                        if (condition(input)) {
                            textFieldState.edit {
                                append(input)
                            }
                        }
                    }
                )
                FiniusKeyboardButton(
                    content = { Text(text = "9") },
                    onClick = {
                        val input = "9"
                        if (condition(input)) {
                            textFieldState.edit {
                                append(input)
                            }
                        }
                    }
                )
            }
            Row(modifier = rowModifier, verticalAlignment = Alignment.CenterVertically) {
                FiniusKeyboardButton(
                    content = { Text(text = ".") },
                    onClick = {}
                )
                FiniusKeyboardButton(
                    content = { Text(text = "0") },
                    onClick = {
                        val input = "0"
                        if (condition(input)) {
                            textFieldState.edit {
                                append(input)
                            }
                        }
                    }
                )
                FiniusKeyboardButton(
                    content = {
                        Icon(
                            painter = painterResource(id = R.drawable.delete_light),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    onClick = {
                        textFieldState.edit {
                            val text = textFieldState.text
                            if (text.isNotEmpty()) {
                                delete(text.length - 1, text.length) }
                            }
                    }
                )
            }
        }
    }
}

@Composable
fun RowScope.FiniusKeyboardButton(
    content: @Composable () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .weight(1f)
            .fillMaxSize()
            .clip(RoundedCornerShape(100))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Preview
@Composable
private fun FiniusNumberKeyboardPreview() {
    FiniusTheme {
        FiniusNumberKeyboard(
            textFieldState = TextFieldState()
        )
    }
}