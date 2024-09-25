package br.com.finius.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.finius.R
import br.com.finius.domain.model.Money
import br.com.finius.ui.theme.FiniusTheme

data class InputFieldTrailing(
    val iconRes: Int,
    val action: (() -> Unit)? = null
)

@Composable
fun InputField(
    label: String,
    state: TextFieldState,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.SingleLine,
    inputTransformation: InputTransformation? = null,
    outputTransformation: OutputTransformation? = null,
    trailing: InputFieldTrailing? = null,
) {
    Column(modifier.padding(horizontal = 24.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = label, style = MaterialTheme.typography.labelSmall)
        Row(
            modifier = Modifier
                .height(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BasicTextField(
                state = state,
                modifier = Modifier.weight(1f),
                readOnly = readOnly,
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface
                ),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
                keyboardOptions = keyboardOptions,
                onKeyboardAction = onKeyboardAction,
                lineLimits = lineLimits,
                inputTransformation = inputTransformation,
                outputTransformation = outputTransformation
            )

            if (trailing != null) {
                Box(modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .clickable { trailing.action?.invoke() }
                    .padding(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = trailing.iconRes),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun InputFieldPreview() {
    FiniusTheme {
        InputField(
            label = "Label",
            state = rememberTextFieldState(),
            trailing = InputFieldTrailing(R.drawable.caretdown)
        )
    }
}

val currencyOutputTransformation = OutputTransformation {
    val cents = (originalText.toString().toLongOrNull() ?: 0L)
    val money = Money(cents)
    replace(0, length, money.format())
}