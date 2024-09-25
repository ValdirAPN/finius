@file:OptIn(ExperimentalMaterial3Api::class)

package br.com.finius.features.transaction

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.finius.R
import br.com.finius.domain.model.Colors
import br.com.finius.domain.model.PaymentAccount
import br.com.finius.domain.model.PaymentAccountType
import br.com.finius.domain.model.TransactionType
import br.com.finius.ui.components.Button
import br.com.finius.ui.components.HorizontalSelector
import br.com.finius.ui.components.InputField
import br.com.finius.ui.components.NavigationLeading
import br.com.finius.ui.components.Select
import br.com.finius.ui.components.Toolbar
import br.com.finius.ui.components.currencyOutputTransformation
import br.com.finius.ui.theme.FiniusTheme
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
object NewTransactionRoute

@Composable
fun NewTransactionScreen(onNavigateBack: () -> Unit, onNavigateToNewCard: () -> Unit) {

    val viewModel = koinViewModel<NewTransactionViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getCards()
    }

    Content(
        uiState = uiState,
        onNavigateBack = onNavigateBack,
        onNavigateToNewCard = onNavigateToNewCard,
        onSetTransactionType = viewModel::setTransactionType,
        onSetPaymentAccountType = viewModel::setPaymentAccountType,
        onSetPaymentAccount = viewModel::setPaymentAccount,
        onCreateTransaction = viewModel::createTransaction
    )

}

@Composable
private fun Content(
    uiState: NewTransactionUiState,
    onNavigateBack: () -> Unit,
    onNavigateToNewCard: () -> Unit,
    onSetTransactionType: (TransactionType) -> Unit,
    onSetPaymentAccount: (PaymentAccount) -> Unit,
    onSetPaymentAccountType: (PaymentAccountType) -> Unit,
    onCreateTransaction: () -> Unit
) {
    with(uiState) {

        val sheetState = rememberModalBottomSheetState()
        var showBottomSheet by remember {
            mutableStateOf(false)
        }

        Scaffold(
            topBar = {
                Toolbar(
                    label = "Nova Transação",
                    leading = NavigationLeading.Close(action = onNavigateBack),
                )
            },
            bottomBar = {
                Box(modifier = Modifier.padding(24.dp)) {
                    Button(
                        onClick = onCreateTransaction,
                        label = "Finalizar",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                HorizontalSelector(
                    *TransactionType.entries.toTypedArray(),
                    selectedItem = type,
                    itemToString = { transactionType ->
                        when (transactionType) {
                            TransactionType.Expense -> "Despesa"
                            TransactionType.Income -> "Receita"
                        }
                    },
                    onSelectItem = onSetTransactionType
                )

                InputField(
                    label = "Título",
                    state = rememberTextFieldState(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )

                InputField(
                    label = "Valor",
                    state = rememberTextFieldState(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword,
                        imeAction = ImeAction.Next
                    ),
                    outputTransformation = currencyOutputTransformation
                )

                HorizontalSelector(
                    *PaymentAccountType.entries.toTypedArray(),
                    selectedItem = paymentAccountType,
                    itemToString = { paymentAccountType ->
                        when (paymentAccountType) {
                            PaymentAccountType.Bank -> "Débito"
                            PaymentAccountType.Card -> "Crédito"
                        }
                    },
                    onSelectItem = onSetPaymentAccountType
                )

                Select(
                    label = "Cartão",
                    state = rememberTextFieldState(),
                    onClick = { showBottomSheet = !showBottomSheet }
                )

                InputField(
                    label = "Parcelas",
                    state = rememberTextFieldState(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword,
                        imeAction = ImeAction.Next
                    )
                )

                DatePicker(state = dateState, title = null, headline = null, showModeToggle = false)
            }

            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showBottomSheet = false },
                    sheetState = sheetState
                ) {
                    CardSelector(
                        cards = emptyList(),
                        onTapCreateNewCard = {
                            showBottomSheet = false
                            onNavigateToNewCard()
                        },
                        onSelectCard = {
                            showBottomSheet = false
                            onSetPaymentAccount(it)
                        }
                    )
                }
            }
        }
    }
}

@Composable
internal fun CardSelector(
    cards: List<PaymentAccount>,
    onTapCreateNewCard: () -> Unit,
    onSelectCard: (PaymentAccount) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .clickable { onTapCreateNewCard() }
                .padding(12.dp),
        ) {
            Text(text = "Criar novo cartão")
            Icon(painter = painterResource(id = R.drawable.plus), contentDescription = null)
        }

        cards.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onSelectCard(it) }
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(100))
                    .background(it.color.color))
                Text(text = it.name)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardSelectorPreview() {
    FiniusTheme {
        CardSelector(
            cards = listOf(
                PaymentAccount(
                    id = "id",
                    name = "Nubank",
                    balance = 3499.99,
                    type = PaymentAccountType.Card,
                    dueDay = null,
                    color = Colors.Mauve
                )
            ),
            onTapCreateNewCard = {},
            onSelectCard = {}
        )
    }
}