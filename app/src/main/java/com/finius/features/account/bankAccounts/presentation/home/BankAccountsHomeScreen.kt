package com.finius.features.account.bankAccounts.presentation.home

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.finius.R
import com.finius.core.domain.Account
import com.finius.features.account.bankAccounts.presentation.form.name.BankAccountNameScreen
import com.finius.ui.components.FiniusButton
import com.finius.ui.components.FiniusButtonSize
import com.finius.ui.components.FiniusButtonVariant
import com.finius.ui.components.FiniusListItem
import com.finius.ui.components.FiniusNavigationBar
import com.finius.ui.components.FiniusNavigationBarLeadingAction
import com.finius.ui.theme.FiniusTheme

class AccountsHomeScreen : Screen {
    @Composable
    override fun Content() {

        val screenModel = rememberScreenModel<AccountsHomeScreenModel>()
        val state by screenModel.uiState.collectAsStateWithLifecycle()

        val navigator = LocalNavigator.currentOrThrow

        val accountHomeStrings = strings.bankAccountStrings.bankAccountsHomeStrings

        AccountsHomeScreenContent(
            strings = accountHomeStrings,
            accounts = state.accounts,
            onClickNavigationIcon = { navigator.popUntilRoot() },
            onClickNewAccount = { navigator.push(BankAccountNameScreen()) }
        )
    }
}

@Composable
fun AccountsHomeScreenContent(
    strings: BankAccountsHomeStrings,
    accounts: List<Account>,
    onClickNavigationIcon: () -> Unit,
    onClickNewAccount: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            FiniusNavigationBar(
                title = strings.title,
                leadingAction = FiniusNavigationBarLeadingAction.Close(action = onClickNavigationIcon)
            )

            Column(
                modifier = Modifier.padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                FiniusButton(
                    text = strings.btnLabel,
                    onClick = onClickNewAccount,
                    trailingIconRes = R.drawable.plus_light,
                    modifier = Modifier.fillMaxWidth(),
                    variant = FiniusButtonVariant.PrimaryGhost,
                    size = FiniusButtonSize.Medium
                )
                LazyColumn {
                    items(items = accounts, key = { account -> account.id }) { account ->
                        FiniusListItem(label = account.name, leadingContent = {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(100))
                                    .background(MaterialTheme.colorScheme.onBackground)
                                    .padding(4.dp)
                            ) {
                                Icon(
                                    modifier = Modifier.size(28.dp),
                                    painter = painterResource(id = account.brand.iconRes),
                                    contentDescription = null,
                                    tint = Color.Unspecified
                                )
                            }
                        })
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun AccountsHomeScreenContentPreview() {
    FiniusTheme {
        val accounts = Account.createFakeAccounts()
        AccountsHomeScreenContent(
            strings = strings.bankAccountStrings.bankAccountsHomeStrings,
            accounts = accounts,
            onClickNavigationIcon = {},
            onClickNewAccount = {})
    }
}