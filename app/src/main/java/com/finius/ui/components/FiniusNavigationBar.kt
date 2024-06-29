package com.finius.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.finius.R
import com.finius.ui.theme.FiniusTheme

@Composable
fun FiniusNavigationBar(
    title: String?,
    leadingAction: FiniusNavigationBarLeadingAction,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    primaryTrailingAction: FiniusNavigationBarTrailingAction? = null,
    secondaryTrailingAction: FiniusNavigationBarTrailingAction? = null,
) {

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = leadingAction.action
            ) {
                Icon(
                    painter = painterResource(id = leadingAction.iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }

            Row {
                if (secondaryTrailingAction != null) {
                    IconButton(onClick = secondaryTrailingAction.action) {
                        Icon(
                            painter = painterResource(id = secondaryTrailingAction.iconRes),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                if (primaryTrailingAction != null) {
                    IconButton(onClick = primaryTrailingAction.action) {
                        Icon(
                            painter = painterResource(id = primaryTrailingAction.iconRes),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }

        title?.let { title ->
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineMedium
                )

                subtitle?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FiniusToolbarPreviewWithBackLeading() {
    FiniusTheme {
        Column {
            FiniusNavigationBar(
                title = "A big title so we can see how it behaves in a small area",
                leadingAction = FiniusNavigationBarLeadingAction.Back(action = {})
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FiniusToolbarPreviewWithCloseLeading() {
    FiniusTheme {
        Column {
            FiniusNavigationBar(
                title = "A big title so we can see how it behaves in a small area",
                leadingAction = FiniusNavigationBarLeadingAction.Close(action = {})
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FiniusToolbarPreviewWithLeadingAndPrimaryTrailing() {
    FiniusTheme {
        Column {
            FiniusNavigationBar(
                title = "A big title so we can see how it behaves in a small area",
                leadingAction = FiniusNavigationBarLeadingAction.Back(action = {}),
                primaryTrailingAction = FiniusNavigationBarTrailingAction(
                    iconRes = R.drawable.plus_light,
                    action = {})
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FiniusToolbarPreviewWithLeadingPrimaryAndSecondaryTrailing() {
    FiniusTheme {
        Column {
            FiniusNavigationBar(
                title = "A big title so we can see how it behaves in a small area",
                subtitle = "An even larger subtitle so we can see how it behaves in a small area",
                leadingAction = FiniusNavigationBarLeadingAction.Back(action = {}),
                primaryTrailingAction = FiniusNavigationBarTrailingAction(
                    iconRes = R.drawable.plus_light,
                    action = {}
                ),
                secondaryTrailingAction = FiniusNavigationBarTrailingAction(
                    iconRes = R.drawable.arrows_counter_clockwise_light,
                    action = {}
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FiniusToolbarPreviewWithNoTitle() {
    FiniusTheme {
        Column {
            FiniusNavigationBar(
                title = null,
                leadingAction = FiniusNavigationBarLeadingAction.Back(action = {}),
                primaryTrailingAction = FiniusNavigationBarTrailingAction(
                    iconRes = R.drawable.plus_light,
                    action = {}
                ),
                secondaryTrailingAction = FiniusNavigationBarTrailingAction(
                    iconRes = R.drawable.arrows_counter_clockwise_light,
                    action = {}
                )
            )
        }
    }
}

sealed class FiniusNavigationBarLeadingAction(
    val iconRes: Int,
    open val action: () -> Unit
) {
    data class Close(
        override val action: () -> Unit
    ) : FiniusNavigationBarLeadingAction(
        iconRes = R.drawable.close_light,
        action = action
    )

    data class Back(
        override val action: () -> Unit
    ) : FiniusNavigationBarLeadingAction(
        iconRes = R.drawable.arrow_left_light,
        action = action
    )
}

data class FiniusNavigationBarTrailingAction(
    val iconRes: Int,
    val action: () -> Unit
)