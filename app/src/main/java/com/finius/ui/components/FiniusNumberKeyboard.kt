package com.finius.ui.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.finius.R
import com.finius.ui.theme.FiniusTheme

@Composable
fun FiniusNumberKeyboard(textFieldState: TextFieldState, modifier: Modifier = Modifier) {
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
                        textFieldState.edit { append("1") }
                    }
                )
                FiniusKeyboardButton(
                    content = { Text(text = "2") },
                    onClick = {
                        textFieldState.edit { append("2") }
                    }
                )
                FiniusKeyboardButton(
                    content = { Text(text = "3") },
                    onClick = {
                        textFieldState.edit { append("3") }
                    }
                )
            }
            Row(modifier = rowModifier, verticalAlignment = Alignment.CenterVertically) {
                FiniusKeyboardButton(
                    content = { Text(text = "4") },
                    onClick = {
                        textFieldState.edit { append("4") }
                    }
                )
                FiniusKeyboardButton(
                    content = { Text(text = "5") },
                    onClick = {
                        textFieldState.edit { append("5") }
                    }
                )
                FiniusKeyboardButton(
                    content = { Text(text = "6") },
                    onClick = {
                        textFieldState.edit { append("6") }
                    }
                )
            }
            Row(modifier = rowModifier, verticalAlignment = Alignment.CenterVertically) {
                FiniusKeyboardButton(
                    content = { Text(text = "7") },
                    onClick = {
                        textFieldState.edit { append("7") }
                    }
                )
                FiniusKeyboardButton(
                    content = { Text(text = "8") },
                    onClick = {
                        textFieldState.edit { append("8") }
                    }
                )
                FiniusKeyboardButton(
                    content = { Text(text = "9") },
                    onClick = {
                        textFieldState.edit { append("9") }
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
                        textFieldState.edit { append("0") }
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
                        textFieldState.edit { delete(textFieldState.text.length - 1, textFieldState.text.length) }
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