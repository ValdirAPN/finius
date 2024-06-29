package com.finius.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.finius.R
import com.finius.ui.theme.BlueGrey20
import com.finius.ui.theme.BlueGrey30
import com.finius.ui.theme.CyanGrey10
import com.finius.ui.theme.FiniusTheme
import com.finius.ui.theme.Green40

enum class FiniusButtonState(
    val contentColor: Color,
    val color: Color
) {
    DEFAULT(
        contentColor = CyanGrey10,
        color = Green40
    ),
    DISABLED(
        contentColor = BlueGrey20,
        color = BlueGrey30
    )
}

@Composable
fun FiniusButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    trailingIconRes: Int? = null,
    state: FiniusButtonState = FiniusButtonState.DEFAULT
) {
    with(state) {
        Surface(
            modifier = modifier,
            onClick = onClick,
            shape = RoundedCornerShape(12.dp),
            enabled = state != FiniusButtonState.DISABLED,
            color = color,
            contentColor = contentColor
        ) {
            Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(text = text, style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium))
                Spacer(modifier = Modifier.size(8.dp))
                trailingIconRes?.let { iconRes ->
                    Icon(painter = painterResource(id = iconRes), contentDescription = null)
                }
            }
        }
    }
}

@Preview
@Composable
private fun FiniusButtonDefaultPreview() {
    FiniusTheme {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            FiniusButton(text = "Continuar", onClick = { /*TODO*/ })
            FiniusButton(text = "Continuar", onClick = { /*TODO*/ }, trailingIconRes = R.drawable.arrow_right_light)
            FiniusButton(text = "Continuar", onClick = { /*TODO*/ }, trailingIconRes = R.drawable.arrow_right_light, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Preview
@Composable
private fun FiniusButtonDisabledPreview() {
    FiniusTheme {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            FiniusButton(text = "Continuar", onClick = { /*TODO*/ }, state = FiniusButtonState.DISABLED)
            FiniusButton(text = "Continuar", onClick = { /*TODO*/ }, state = FiniusButtonState.DISABLED, trailingIconRes = R.drawable.arrow_right_light)
            FiniusButton(text = "Continuar", onClick = { /*TODO*/ }, state = FiniusButtonState.DISABLED, trailingIconRes = R.drawable.arrow_right_light, modifier = Modifier.fillMaxWidth())
        }
    }
}