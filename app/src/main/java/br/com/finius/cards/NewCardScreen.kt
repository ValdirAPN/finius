package br.com.finius.cards

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
import br.com.finius.ui.components.Button
import br.com.finius.ui.components.ColorSelector
import br.com.finius.ui.components.Colors
import br.com.finius.ui.components.InputField
import br.com.finius.ui.components.NavigationBar
import br.com.finius.ui.components.NavigationLeading
import kotlinx.serialization.Serializable

@Serializable
object NewCardRoute

@Composable
fun NewCardScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {

    var selectedColor by remember {
        mutableStateOf(Colors.entries.first())
    }

    Scaffold(
        modifier,
        topBar = {
            NavigationBar(
                label = "Novo Cartão",
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
                .fillMaxSize()
        ) {
            InputField(label = "Nome", state = rememberTextFieldState())
            InputField(label = "Limite", state = rememberTextFieldState())
            InputField(label = "Vencimento", state = rememberTextFieldState())
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
