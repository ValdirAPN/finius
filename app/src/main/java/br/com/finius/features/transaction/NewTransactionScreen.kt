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
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.finius.R
import br.com.finius.domain.model.Colors
import br.com.finius.domain.model.Money
import br.com.finius.domain.model.PaymentAccount
import br.com.finius.domain.model.PaymentAccountType
import br.com.finius.domain.model.TransactionCategory
import br.com.finius.domain.model.TransactionType
import br.com.finius.domain.model.getExhibitionName
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
fun NewTransactionScreen(
    onNavigateBack: () -> Unit,
    onNavigateToNewCard: () -> Unit,
    onNavigateToNewBank: () -> Unit
) {

    val viewModel = koinViewModel<NewTransactionViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getCards()
        viewModel.getBanks()
    }

    Content(
        uiState = uiState,
        onNavigateBack = onNavigateBack,
        onNavigateToNewCard = onNavigateToNewCard,
        onNavigateToNewBank = onNavigateToNewBank,
        onSetTransactionType = viewModel::setTransactionType,
        onSetPaymentAccount = viewModel::setPaymentAccount,
        onSetCategory = viewModel::setCategory,
        onSetInstallments = viewModel::setInstallments,
        onCreateTransaction = {
            viewModel.createTransaction()
            onNavigateBack()
        }
    )

}

@Composable
private fun Content(
    uiState: NewTransactionUiState,
    onNavigateBack: () -> Unit,
    onNavigateToNewCard: () -> Unit,
    onNavigateToNewBank: () -> Unit,
    onSetTransactionType: (TransactionType) -> Unit,
    onSetPaymentAccount: (PaymentAccount) -> Unit,
    onSetCategory: (TransactionCategory) -> Unit,
    onSetInstallments: (Int) -> Unit,
    onCreateTransaction: () -> Unit
) {
    with(uiState) {

        val sheetState = rememberModalBottomSheetState()

        var showPaymentAccountBottomSheet by remember {
            mutableStateOf(false)
        }

        var showInstallmentsBottomSheet by remember {
            mutableStateOf(false)
        }

        var showCategoriesBottomSheet by remember {
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
                    .padding(horizontal = 24.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                HorizontalSelector(
                    *TransactionType.entries.toTypedArray(),
                    selectedItem = type,
                    itemToString = { transactionType ->
                        when (transactionType) {
                            TransactionType.EXPENSE -> "Despesa"
                            TransactionType.INCOME -> "Receita"
                        }
                    },
                    onSelectItem = onSetTransactionType
                )

                InputField(
                    label = "Título",
                    state = title,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )

                InputField(
                    label = "Valor",
                    state = amount,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword,
                        imeAction = ImeAction.Next
                    ),
                    outputTransformation = currencyOutputTransformation
                )

                Select(
                    label = "Conta/Cartão",
                    state = TextFieldState(initialText = paymentAccount?.name ?: ""),
                    onClick = { showPaymentAccountBottomSheet = !showPaymentAccountBottomSheet }
                )

                Select(
                    label = "Parcelas",
                    state = TextFieldState(initialText = installments.toString()),
                    onClick = { showInstallmentsBottomSheet = !showInstallmentsBottomSheet }
                )

                Select(
                    label = "Categoria",
                    state = TextFieldState(initialText = category.getExhibitionName()),
                    onClick = { showCategoriesBottomSheet = !showCategoriesBottomSheet }
                )

                DatePicker(state = dateState, title = null, headline = null, showModeToggle = false)
            }

            if (showPaymentAccountBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showPaymentAccountBottomSheet = false },
                    sheetState = sheetState
                ) {
                    PaymentAccountSelector(
                        cards = cards,
                        banks = banks,
                        onTapCreateNewCard = {
                            showPaymentAccountBottomSheet = false
                            onNavigateToNewCard()
                        },
                        onTapCreateNewBankAccount = {
                            showPaymentAccountBottomSheet = false
                            onNavigateToNewBank()
                        },
                        onTapItem = {
                            showPaymentAccountBottomSheet = false
                            onSetPaymentAccount(it)
                        }
                    )
                }
            }

            if (showInstallmentsBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showPaymentAccountBottomSheet = false },
                    sheetState = sheetState
                ) {
                    InstallmentsSelector(
                        onTapItem = {
                            showInstallmentsBottomSheet = false
                            onSetInstallments(it)
                        }
                    )
                }
            }

            if (showCategoriesBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showCategoriesBottomSheet = false },
                    sheetState = sheetState
                ) {
                    CategoriesSelector(
                        categories = TransactionCategory.entries,
                        onTapCategory = {
                            showCategoriesBottomSheet = false
                            onSetCategory(it)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoriesSelector(
    categories: List<TransactionCategory>,
    onTapCategory: (TransactionCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {

        categories.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onTapCategory(it) }
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(100))
                        .background(it.color.value),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = it.iconRes),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
                Text(text = it.getExhibitionName())
            }
        }
    }
}

@Composable
private fun InstallmentsSelector(
    onTapItem: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    val installmentsTextFieldState = rememberTextFieldState()
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            InputField(
                modifier = Modifier.weight(1f),
                placeholder = "Digite um valor",
                state = installmentsTextFieldState,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword,
                    imeAction = ImeAction.Next
                )
            )
            Button(
                onClick = {
                    onTapItem(
                        installmentsTextFieldState.text.toString().toIntOrNull() ?: 1
                    )
                },
                label = "Ok"
            )
        }

        Column {
            (1..12).forEach { number ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { onTapItem(number) }
                        .padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = number.toString(),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
private fun PaymentAccountSelector(
    cards: List<PaymentAccount>,
    banks: List<PaymentAccount>,
    onTapCreateNewCard: () -> Unit,
    onTapCreateNewBankAccount: () -> Unit,
    onTapItem: (PaymentAccount) -> Unit,
    modifier: Modifier = Modifier
) {

    var selectedPaymentAccountType by remember {
        mutableStateOf(PaymentAccountType.entries.first())
    }

    Column(
        modifier = modifier
            .padding(vertical = 16.dp, horizontal = 24.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HorizontalSelector(
            *PaymentAccountType.entries.toTypedArray(),
            selectedItem = selectedPaymentAccountType,
            itemToString = { paymentAccountType ->
                when (paymentAccountType) {
                    PaymentAccountType.CARD -> "Cartões"
                    PaymentAccountType.BANK -> "Contas"
                }
            },
            onSelectItem = { selectedPaymentAccountType = it }
        )

        Column {
            if (selectedPaymentAccountType == PaymentAccountType.CARD) {
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
                            .clickable { onTapItem(it) }
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(100))
                                .background(it.color.value)
                        )
                        Text(text = it.name)
                    }
                }
            }

            if (selectedPaymentAccountType == PaymentAccountType.BANK) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { onTapCreateNewBankAccount() }
                        .padding(12.dp),
                ) {
                    Text(text = "Criar nova conta")
                    Icon(painter = painterResource(id = R.drawable.plus), contentDescription = null)
                }

                banks.forEach {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { onTapItem(it) }
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(100))
                                .background(it.color.value)
                        )
                        Text(text = it.name)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardSelectorPreview() {
    FiniusTheme {
        PaymentAccountSelector(
            cards = listOf(
                PaymentAccount(
                    id = "id",
                    name = "Nubank",
                    balance = Money(349999),
                    type = PaymentAccountType.CARD,
                    dueDay = 2,
                    color = Colors.Mauve
                )
            ),
            banks = listOf(
                PaymentAccount(
                    id = "id",
                    name = "Nubank",
                    balance = Money(349999),
                    type = PaymentAccountType.CARD,
                    dueDay = null,
                    color = Colors.Mauve
                )
            ),
            onTapCreateNewCard = {},
            onTapCreateNewBankAccount = {},
            onTapItem = {}
        )
    }
}