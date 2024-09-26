package br.com.finius.features.bankAccounts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.finius.domain.model.Colors
import br.com.finius.features.cards.NewCardViewModel
import br.com.finius.ui.components.Button
import br.com.finius.ui.components.ColorSelector
import br.com.finius.ui.components.InputField
import br.com.finius.ui.components.Toolbar
import br.com.finius.ui.components.NavigationLeading
import br.com.finius.ui.components.currencyOutputTransformation
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
object NewBankAccountRoute

@Composable
fun NewBankAccountScreen(
    onNavigateBack: () -> Unit,
) {

    val viewModel = koinViewModel<NewBankAccountViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Content(
        uiState = uiState,
        onCreateBankAccount = viewModel::create,
        onSetColor = viewModel::setColor,
        onNavigateBack = onNavigateBack
    )
}

@Composable
private fun Content(
    uiState: NewBankAccountUiState,
    onCreateBankAccount: () -> Unit,
    onSetColor: (Colors) -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    with(uiState) {
        Scaffold(
            modifier,
            topBar = {
                Toolbar(
                    label = "Nova Conta",
                    leading = NavigationLeading.Back(action = onNavigateBack),
                )
            },
            bottomBar = {
                Box(modifier = Modifier.padding(24.dp)) {
                    Button(
                        onClick = {
                            onCreateBankAccount()
                            onNavigateBack()
                        },
                        label = "Finalizar",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                InputField(label = "Nome", state = name)
                InputField(
                    label = "Saldo",
                    state = balance,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    outputTransformation = currencyOutputTransformation
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(horizontal = 24.dp)
                ) {
                    Text(text = "Cor", style = MaterialTheme.typography.labelSmall)
                    ColorSelector(
                        selectedColor = color,
                        onSelectColor = { color -> onSetColor(color) })
                }
            }
        }
    }
}