package com.finius.features.transaction.presentation.account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.finius.R
import com.finius.core.domain.Account
import com.finius.core.domain.BankAccount
import com.finius.core.domain.CreditCard
import com.finius.core.domain.TransactionType
import com.finius.features.transaction.presentation.title.TransactionTitleScreen
import com.finius.ui.components.FiniusButton
import com.finius.ui.components.FiniusButtonState
import com.finius.ui.components.FiniusDivider
import com.finius.ui.components.FiniusListItem
import com.finius.ui.components.FiniusListItemState
import com.finius.ui.components.FiniusNavigationBar
import com.finius.ui.components.FiniusNavigationBarLeadingAction
import com.finius.ui.theme.FiniusTheme

data class TransactionAccountScreen(
    val transactionType: TransactionType
) : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val transactionAccountStrings = strings.transactionStrings.accountStrings

        val screenModel = rememberScreenModel<TransactionAccountScreenModel>()
        val state by screenModel.state.collectAsState()

        LaunchedEffect(Unit) {
            screenModel.assemble(transactionType)
        }

        TransactionAccountScreenContent(
            strings = transactionAccountStrings,
            transactionType = transactionType,
            accounts = state.accounts,
            onClickNavigationIcon = navigator::pop,
            onClickContinue = { navigator.push(TransactionTitleScreen()) }
        )
    }
}

@Composable
fun TransactionAccountScreenContent(
    strings: TransactionAccountStrings,
    transactionType: TransactionType,
    accounts: List<Account>,
    onClickNavigationIcon: () -> Unit,
    onClickContinue: () -> Unit,
    modifier: Modifier = Modifier
) {

    var selectedAccount by remember {
        mutableStateOf<Account?>(null)
    }

    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            Column(
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                FiniusNavigationBar(
                    title = strings.title(transactionType),
                    subtitle = strings.subtitle(transactionType),
                    leadingAction = FiniusNavigationBarLeadingAction.Back(action = onClickNavigationIcon)
                )

                AccountsContainer(
                    strings = strings,
                    accounts = accounts,
                    selectedAccount = selectedAccount,
                    onClickAccount = { account -> selectedAccount = account },
                )
            }
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                FiniusButton(
                    text = strings.btnLabel,
                    onClick = onClickContinue,
                    state = if (selectedAccount != null) FiniusButtonState.Default else FiniusButtonState.Disabled,
                    modifier = Modifier.fillMaxWidth(),
                    trailingIconRes = R.drawable.arrow_right_light
                )
            }
        }
    }
}

@Composable
private fun AccountsContainer(
    strings: TransactionAccountStrings,
    accounts: List<Account>,
    selectedAccount: Account?,
    onClickAccount: (Account) -> Unit,
    modifier: Modifier = Modifier
) {

    val bankAccounts = remember(accounts) {
        accounts.filterIsInstance<BankAccount>()
    }

    val cardAccounts = remember(accounts) {
        accounts.filterIsInstance<CreditCard>()
    }

    Column(
        modifier = modifier.padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (bankAccounts.isNotEmpty()) {
            AccountsList(
                accounts = bankAccounts,
                onClickAccount = onClickAccount,
                selectedAccount = selectedAccount,
                label = strings.bankAccountsLabel
            )
        }

        if (cardAccounts.isNotEmpty()) {
            AccountsList(
                accounts = cardAccounts,
                onClickAccount = onClickAccount,
                selectedAccount = selectedAccount,
                label = strings.creditCardAccountsLabel
            )
        }
    }

}

@Composable
fun AccountsList(
    accounts: List<Account>,
    onClickAccount: (Account) -> Unit,
    selectedAccount: Account?,
    modifier: Modifier = Modifier,
    label: String? = null,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        label?.let {
            Text(
                text = it,
                modifier = Modifier.padding(horizontal = 8.dp),
                style = MaterialTheme.typography.labelMedium
            )
        }
        LazyColumn {
            items(accounts, key = { account -> account.id }) { account ->
                FiniusListItem(
                    label = account.name,
                    onClick = { onClickAccount(account) },
                    state = if (account.id == selectedAccount?.id) FiniusListItemState.Selected else FiniusListItemState.Default,
                    leadingContent = {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(100))
                                .background(MaterialTheme.colorScheme.onBackground)
                                .padding(4.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = account.brand.iconRes),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                )

                if (account != accounts.last()) {
                    FiniusDivider(
                        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun TransactionAccountScreenContentIncomePreview() {
    FiniusTheme {

        val transactionAccountStrings = strings.transactionStrings.accountStrings
        val accounts = remember { Account.createFakeAccounts() }

        Surface(color = MaterialTheme.colorScheme.background) {
            TransactionAccountScreenContent(
                strings = transactionAccountStrings,
                transactionType = TransactionType.INCOME,
                accounts = accounts,
                onClickNavigationIcon = {},
                onClickContinue = {}
            )
        }
    }
}

@Preview
@Composable
private fun TransactionAccountScreenContentExpensePreview() {
    FiniusTheme {

        val transactionAccountStrings = strings.transactionStrings.accountStrings
        val accounts = remember { Account.createFakeAccounts() }

        Surface(color = MaterialTheme.colorScheme.background) {
            TransactionAccountScreenContent(
                strings = transactionAccountStrings,
                transactionType = TransactionType.EXPENSE,
                accounts = accounts,
                onClickNavigationIcon = {},
                onClickContinue = {}
            )
        }
    }
}