package com.finius.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.finius.ui.theme.BlueGrey40
import com.finius.ui.theme.FiniusTheme

@Composable
fun FiniusInputField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    label: String? = null,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    outputTransformation: OutputTransformation? = null,
    inputTransformation: InputTransformation? = null,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        label?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.labelMedium
            )
        }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .border(1.dp, BlueGrey40, RoundedCornerShape(8.dp))
                .fillMaxWidth()
        ) {
            BasicTextField(
                state = state,
                readOnly = readOnly,
                textStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onBackground),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
                keyboardOptions = keyboardOptions,
                outputTransformation = outputTransformation,
                inputTransformation = inputTransformation,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
private fun FiniusInputFieldPreview() {
    FiniusTheme {
        Surface {
            Column {
                FiniusInputField(
                    state = TextFieldState(),
                    label = "Label",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}