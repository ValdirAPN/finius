package com.finius.features.account.bankAccounts.presentation.form.brand

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.remember
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
import com.finius.core.domain.AccountBrand
import com.finius.features.account.bankAccounts.presentation.form.AccountFormScreenModel
import com.finius.features.account.bankAccounts.presentation.form.balance.AccountBalanceScreen
import com.finius.ui.components.FiniusButton
import com.finius.ui.components.FiniusButtonState
import com.finius.ui.components.FiniusListItem
import com.finius.ui.components.FiniusListItemState
import com.finius.ui.components.FiniusNavigationBar
import com.finius.ui.components.FiniusNavigationBarLeadingAction
import com.finius.ui.theme.FiniusTheme

class BankAccountBrandScreen : Screen {
    @Composable
    override fun Content() {

        val screenModel = rememberScreenModel<AccountFormScreenModel>()
        val state by screenModel.uiState.collectAsStateWithLifecycle()

        val navigator = LocalNavigator.currentOrThrow

        val accountBrands = remember {
            AccountBrand.entries
        }

        val bankAccountBrandStrings = strings.bankAccountStrings.bankAccountFormStrings.bankAccountBrandStrings

        BankAccountBrandScreenContent(
            strings = bankAccountBrandStrings,
            accountBrands = accountBrands,
            selectedBrand = state.brand,
            onClickBrand = screenModel::setBrand,
            onClickNavigationIcon = { navigator.pop() },
            onClickContinue = { navigator.push(AccountBalanceScreen()) }
        )
    }
}

@Composable
fun BankAccountBrandScreenContent(
    strings: BankAccountBrandStrings,
    accountBrands: List<AccountBrand>,
    selectedBrand: AccountBrand?,
    onClickBrand: (brand: AccountBrand) -> Unit,
    onClickNavigationIcon: () -> Unit,
    onClickContinue: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier, color = MaterialTheme.colorScheme.background) {
        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            FiniusNavigationBar(
                title = strings.title,
                leadingAction = FiniusNavigationBarLeadingAction.Back(action = onClickNavigationIcon)
            )

            Column(modifier = Modifier.weight(1f)) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    LazyColumn {
                        items(accountBrands) { brand ->
                            val state = remember(selectedBrand) {
                                if (selectedBrand == brand) {
                                    FiniusListItemState.Selected
                                } else FiniusListItemState.Default
                            }
                            FiniusListItem(
                                label = brand.title,
                                state = state,
                                onClick = { onClickBrand(brand) },
                                leadingContent = {
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(100))
                                            .background(MaterialTheme.colorScheme.onBackground)
                                            .padding(4.dp)
                                    ) {
                                        Icon(
                                            painter = painterResource(id = brand.iconRes),
                                            contentDescription = null,
                                            tint = Color.Unspecified,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
                Column(modifier = Modifier.padding(16.dp)) {
                    FiniusButton(
                        text = strings.btnLabel,
                        onClick = onClickContinue,
                        trailingIconRes = R.drawable.arrow_right_light,
                        state = if (selectedBrand != null) FiniusButtonState.Default else FiniusButtonState.Disabled
                    )
                }
            }


        }
    }
}

@Preview
@Composable
private fun BankAccountBrandScreenContentPreview() {
    FiniusTheme {
        BankAccountBrandScreenContent(
            strings = strings.bankAccountStrings.bankAccountFormStrings.bankAccountBrandStrings,
            accountBrands = AccountBrand.entries,
            selectedBrand = null,
            onClickBrand = {},
            onClickNavigationIcon = {},
            onClickContinue = {}
        )
    }
}