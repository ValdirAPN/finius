package br.com.finius.features.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.finius.domain.model.Colors
import br.com.finius.ui.components.Button
import br.com.finius.ui.components.ColorSelector
import br.com.finius.ui.components.InputField
import br.com.finius.ui.components.Toolbar
import br.com.finius.ui.components.NavigationLeading
import br.com.finius.ui.components.currencyOutputTransformation
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
object NewCardRoute

@Composable
fun NewCardScreen(
    onNavigateBack: () -> Unit
) {

    val viewModel = koinViewModel<NewCardViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Content(
        uiState = uiState,
        onNavigateBack = onNavigateBack,
        onCreateCard = {
            viewModel.create()
            onNavigateBack()
        },
        onSetColor = viewModel::setColor
    )
}

@Composable
private fun Content(
    uiState: NewCardUiState,
    onNavigateBack: () -> Unit,
    onCreateCard: () -> Unit,
    onSetColor: (Colors) -> Unit,
) {
    with(uiState) {
        Scaffold(
            topBar = {
                Toolbar(
                    label = "Novo Cartão",
                    leading = NavigationLeading.Back(action = onNavigateBack),
                )
            },
            bottomBar = {
                Box(modifier = Modifier.padding(24.dp)) {
                    Button(
                        onClick = onCreateCard,
                        label = "Finalizar",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
                    .verticalScroll(rememberScrollState()
                ),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                InputField(label = "Nome", state = name)
                InputField(
                    label = "Limite",
                    state = balance,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    outputTransformation = currencyOutputTransformation
                )
                InputField(
                    label = "Fechamento",
                    state = closingDay,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                )
                InputField(
                    label = "Vencimento",
                    state = dueDay,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(text = "Cor", style = MaterialTheme.typography.labelSmall)
                    ColorSelector(
                        selectedColor = color,
                        onSelectColor = onSetColor,
                    )
                }
            }
        }
    }
}
