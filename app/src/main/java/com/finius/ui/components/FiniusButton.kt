package com.finius.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.finius.R
import com.finius.ui.theme.BlueGrey20
import com.finius.ui.theme.BlueGrey30
import com.finius.ui.theme.CyanGrey10
import com.finius.ui.theme.FiniusTheme
import com.finius.ui.theme.Green40

enum class FiniusButtonState {
    Default, Disabled
}

enum class FiniusButtonVariant(
    val background: Color,
    val contentColor: Color,
    val disabledBackground: Color,
    val disabledContentColor: Color,
) {
    PrimarySolid(
        background = Green40,
        contentColor = CyanGrey10,
        disabledBackground = BlueGrey30,
        disabledContentColor = BlueGrey20
    ),
    PrimaryGhost(
        background = Color.Transparent,
        contentColor = Green40,
        disabledBackground = Color.Transparent,
        disabledContentColor = BlueGrey20
    )
}

enum class FiniusButtonSize(
    val paddingY: Dp,
    val paddingX: Dp,
    val textStyle: @Composable () -> TextStyle,
    val iconSize: Dp,
    val cornerRadius: Dp
) {
    Large(
        paddingY = 12.dp,
        paddingX = 16.dp,
        textStyle = { MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium) },
        iconSize = 24.dp,
        cornerRadius = 12.dp
    ),
    Medium(
        paddingY = 8.dp,
        paddingX = 12.dp,
        textStyle = { MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium) },
        iconSize = 18.dp,
        cornerRadius = 10.dp
    ),
    Small(
        paddingY = 4.dp,
        paddingX = 8.dp,
        textStyle = { MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium) },
        iconSize = 16.dp,
        cornerRadius = 8.dp
    )
}

@Composable
fun FiniusButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    trailingIconRes: Int? = null,
    state: FiniusButtonState = FiniusButtonState.Default,
    variant: FiniusButtonVariant = FiniusButtonVariant.PrimarySolid,
    size: FiniusButtonSize = FiniusButtonSize.Large
) {
    val (background, contentColor) = remember(state, variant) {
        if (state == FiniusButtonState.Disabled) {
            variant.disabledBackground to variant.disabledContentColor
        } else variant.background to variant.contentColor
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(size.cornerRadius))
            .background(background)
            .clickable { onClick() },
    ) {
        Row(
            modifier = Modifier.padding(horizontal = size.paddingX, vertical = size.paddingY),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                style = size.textStyle(),
                modifier = Modifier.weight(1f),
                color = contentColor
            )
            trailingIconRes?.let { iconRes ->
                Spacer(modifier = Modifier.size(8.dp))
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(size.iconSize),
                    tint = contentColor
                )
            }
        }
    }
}

@Preview
@Composable
private fun FiniusButtonPrimarySolidDefaultPreview() {
    FiniusTheme {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    size = FiniusButtonSize.Large
                )
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    trailingIconRes = R.drawable.arrow_right_light,
                    size = FiniusButtonSize.Large
                )
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    trailingIconRes = R.drawable.arrow_right_light,
                    modifier = Modifier.fillMaxWidth(),
                    size = FiniusButtonSize.Large
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    size = FiniusButtonSize.Medium
                )
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    trailingIconRes = R.drawable.arrow_right_light,
                    size = FiniusButtonSize.Medium
                )
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    trailingIconRes = R.drawable.arrow_right_light,
                    modifier = Modifier.fillMaxWidth(),
                    size = FiniusButtonSize.Medium
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    size = FiniusButtonSize.Small
                )
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    trailingIconRes = R.drawable.arrow_right_light,
                    size = FiniusButtonSize.Small
                )
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    trailingIconRes = R.drawable.arrow_right_light,
                    modifier = Modifier.fillMaxWidth(),
                    size = FiniusButtonSize.Small
                )
            }
        }
    }
}

@Preview
@Composable
private fun FiniusButtonPrimarySolidDisabledPreview() {
    FiniusTheme {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    state = FiniusButtonState.Disabled,
                    size = FiniusButtonSize.Large
                )
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    state = FiniusButtonState.Disabled,
                    trailingIconRes = R.drawable.arrow_right_light,
                    size = FiniusButtonSize.Large
                )
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    state = FiniusButtonState.Disabled,
                    trailingIconRes = R.drawable.arrow_right_light,
                    modifier = Modifier.fillMaxWidth(),
                    size = FiniusButtonSize.Large
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    state = FiniusButtonState.Disabled,
                    size = FiniusButtonSize.Medium
                )
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    state = FiniusButtonState.Disabled,
                    trailingIconRes = R.drawable.arrow_right_light,
                    size = FiniusButtonSize.Medium
                )
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    state = FiniusButtonState.Disabled,
                    trailingIconRes = R.drawable.arrow_right_light,
                    modifier = Modifier.fillMaxWidth(),
                    size = FiniusButtonSize.Medium
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    state = FiniusButtonState.Disabled,
                    size = FiniusButtonSize.Small
                )
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    state = FiniusButtonState.Disabled,
                    trailingIconRes = R.drawable.arrow_right_light,
                    size = FiniusButtonSize.Small
                )
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    state = FiniusButtonState.Disabled,
                    trailingIconRes = R.drawable.arrow_right_light,
                    modifier = Modifier.fillMaxWidth(),
                    size = FiniusButtonSize.Small
                )
            }
        }
    }
}

@Preview
@Composable
private fun FiniusButtonPrimaryGhostDefaultPreview() {
    FiniusTheme {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    variant = FiniusButtonVariant.PrimaryGhost,
                    size = FiniusButtonSize.Large
                )
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    variant = FiniusButtonVariant.PrimaryGhost,
                    trailingIconRes = R.drawable.arrow_right_light,
                    size = FiniusButtonSize.Large
                )
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    variant = FiniusButtonVariant.PrimaryGhost,
                    trailingIconRes = R.drawable.arrow_right_light,
                    modifier = Modifier.fillMaxWidth(),
                    size = FiniusButtonSize.Large
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    variant = FiniusButtonVariant.PrimaryGhost,
                    size = FiniusButtonSize.Medium
                )
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    variant = FiniusButtonVariant.PrimaryGhost,
                    trailingIconRes = R.drawable.arrow_right_light,
                    size = FiniusButtonSize.Medium
                )
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    variant = FiniusButtonVariant.PrimaryGhost,
                    trailingIconRes = R.drawable.arrow_right_light,
                    modifier = Modifier.fillMaxWidth(),
                    size = FiniusButtonSize.Medium
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    variant = FiniusButtonVariant.PrimaryGhost,
                    size = FiniusButtonSize.Small
                )
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    variant = FiniusButtonVariant.PrimaryGhost,
                    trailingIconRes = R.drawable.arrow_right_light,
                    size = FiniusButtonSize.Small
                )
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    variant = FiniusButtonVariant.PrimaryGhost,
                    trailingIconRes = R.drawable.arrow_right_light,
                    modifier = Modifier.fillMaxWidth(),
                    size = FiniusButtonSize.Small
                )
            }
        }
    }
}

@Preview
@Composable
private fun FiniusButtonPrimaryGhostDisabledPreview() {
    FiniusTheme {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    variant = FiniusButtonVariant.PrimaryGhost,
                    state = FiniusButtonState.Disabled,
                    size = FiniusButtonSize.Large
                )
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    variant = FiniusButtonVariant.PrimaryGhost,
                    state = FiniusButtonState.Disabled,
                    trailingIconRes = R.drawable.arrow_right_light,
                    size = FiniusButtonSize.Large
                )
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    variant = FiniusButtonVariant.PrimaryGhost,
                    state = FiniusButtonState.Disabled,
                    trailingIconRes = R.drawable.arrow_right_light,
                    modifier = Modifier.fillMaxWidth(),
                    size = FiniusButtonSize.Large
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    variant = FiniusButtonVariant.PrimaryGhost,
                    state = FiniusButtonState.Disabled,
                    size = FiniusButtonSize.Medium
                )
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    variant = FiniusButtonVariant.PrimaryGhost,
                    state = FiniusButtonState.Disabled,
                    trailingIconRes = R.drawable.arrow_right_light,
                    size = FiniusButtonSize.Medium
                )
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    variant = FiniusButtonVariant.PrimaryGhost,
                    state = FiniusButtonState.Disabled,
                    trailingIconRes = R.drawable.arrow_right_light,
                    modifier = Modifier.fillMaxWidth(),
                    size = FiniusButtonSize.Medium
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    variant = FiniusButtonVariant.PrimaryGhost,
                    state = FiniusButtonState.Disabled,
                    size = FiniusButtonSize.Small
                )
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    variant = FiniusButtonVariant.PrimaryGhost,
                    state = FiniusButtonState.Disabled,
                    trailingIconRes = R.drawable.arrow_right_light,
                    size = FiniusButtonSize.Small
                )
                FiniusButton(
                    text = "Continuar",
                    onClick = { /*TODO*/ },
                    variant = FiniusButtonVariant.PrimaryGhost,
                    state = FiniusButtonState.Disabled,
                    trailingIconRes = R.drawable.arrow_right_light,
                    modifier = Modifier.fillMaxWidth(),
                    size = FiniusButtonSize.Small
                )
            }
        }
    }
}