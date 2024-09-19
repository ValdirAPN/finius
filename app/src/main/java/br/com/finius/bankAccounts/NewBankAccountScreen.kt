package br.com.finius.bankAccounts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import br.com.finius.domain.model.Colors
import br.com.finius.ui.components.Button
import br.com.finius.ui.components.ColorSelector
import br.com.finius.ui.components.InputField
import br.com.finius.ui.components.Toolbar
import br.com.finius.ui.components.NavigationLeading
import kotlinx.serialization.Serializable

@Serializable
object NewBankAccountRoute

@Composable
fun NewBankAccountScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {

    var selectedColor by remember {
        mutableStateOf(Colors.entries.first())
    }

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
                    onClick = { /*TODO*/ },
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
            InputField(label = "Nome", state = rememberTextFieldState())
            InputField(label = "Saldo", state = rememberTextFieldState())
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {
                Text(text = "Cor", style = MaterialTheme.typography.labelSmall)
                ColorSelector(
                    selectedColor = selectedColor,
                    onSelectColor = { color -> selectedColor = color })
            }
        }
    }
}
