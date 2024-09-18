package br.com.finius.transaction

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
import br.com.finius.ui.components.HorizontalSelector
import br.com.finius.ui.components.InputField
import br.com.finius.ui.components.NavigationBar
import br.com.finius.ui.components.NavigationLeading
import kotlinx.serialization.Serializable

@Serializable
object NewTransactionRoute

@Composable
fun NewTransactionScreen(onNavigateBack: () -> Unit, modifier: Modifier = Modifier) {

    val transactionTypes = remember {
        arrayOf("Despesa", "Receita")
    }

    var selectedTransactionType by remember(transactionTypes) {
        mutableStateOf(transactionTypes.first())
    }

    val paymentTypes = remember {
        arrayOf("Crédito", "Débito")
    }

    var selectedPaymentType by remember(paymentTypes) {
        mutableStateOf(paymentTypes.first())
    }

    var selectedColor by remember {
        mutableStateOf(Colors.entries.first())
    }

    Scaffold(
        modifier,
        topBar = {
            NavigationBar(
                label = "Nova Transação",
                leading = NavigationLeading.Close(action = onNavigateBack),
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
            HorizontalSelector(
                *transactionTypes,
                selectedItem = selectedTransactionType,
                onSelectItem = { selectedTransactionType = it }
            )

            InputField(label = "Título", state = rememberTextFieldState())

            InputField(label = "Valor", state = rememberTextFieldState())

            HorizontalSelector(
                *paymentTypes,
                selectedItem = selectedPaymentType,
                onSelectItem = { selectedPaymentType = it }
            )

            InputField(label = "Cartão", state = rememberTextFieldState())
            InputField(label = "Parcelas", state = rememberTextFieldState())
            InputField(label = "Data", state = rememberTextFieldState())

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