package com.finius.core.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.finius.R
import com.finius.ui.components.FiniusButton
import com.finius.ui.components.FiniusNavigationBar
import com.finius.ui.components.FiniusNavigationBarLeadingAction
import com.finius.ui.theme.FiniusTheme

class FiniusSuccessScreen(
    val text: String,
    val onClickContinue: () -> Unit
) : Screen {
    @Composable
    override fun Content() {
        FiniusSuccessScreenContent(text = text, onClickContinue = onClickContinue)
    }
}

@Composable
fun FiniusSuccessScreenContent(
    text: String,
    onClickContinue: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier, color = MaterialTheme.colorScheme.background) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            Column(modifier = Modifier.weight(1f)) {
                FiniusNavigationBar(
                    title = null,
                    leadingAction = FiniusNavigationBarLeadingAction.Close(action = onClickContinue)
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxSize()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.success_icon),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.size(24.dp))

                    Text(
                        text = text,
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                FiniusButton(
                    text = "Finalizar",
                    onClick = onClickContinue,
                    modifier = Modifier.fillMaxWidth(),
                    trailingIconRes = R.drawable.arrow_right_light,
                )
            }
        }
    }
}

@Preview
@Composable
private fun FiniusSuccessScreenContentPreview() {
    FiniusTheme {
        FiniusSuccessScreenContent(text = "Transação cadastrada com sucesso!", onClickContinue = {})
    }
}