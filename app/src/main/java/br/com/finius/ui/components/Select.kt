package br.com.finius.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import br.com.finius.R

@Composable
fun Select(
    label: String,
    state: TextFieldState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        InputField(
            label = label,
            state = state,
            readOnly = true,
            trailing = InputFieldTrailing(R.drawable.caretdown, action = onClick)
        )
    }
}