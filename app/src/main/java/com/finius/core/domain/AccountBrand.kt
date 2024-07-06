package com.finius.core.domain

import com.finius.R

enum class AccountBrand(
    val iconRes: Int,
    val title: String
) {

    Wallet(iconRes = R.drawable.wallet_fill, title = "Carteira"),
    Nubank(iconRes = R.drawable.nubank, title = "Nubank"),
    BancoDoBrasil(iconRes = R.drawable.banco_do_brasil, title = "Banco do Brasil"),
    CaixaEconomica(iconRes = R.drawable.caixa_economica, title = "Caixa Econômica"),
    Itau(iconRes = R.drawable.itau, title = "Itaú"),
    Santander(iconRes = R.drawable.santander, title = "Santander"),
    Bradesco(iconRes = R.drawable.bradesco, title = "Bradesco"),
    C6(iconRes = R.drawable.c6, title = "C6 Bank"),
    BankOfAmerica(iconRes = R.drawable.bank_of_america, title = "Bank of America"),
    Sicred(iconRes = R.drawable.sicred, title = "Sicred"),
    Inter(iconRes = R.drawable.inter, title = "Inter"),
    Pan(iconRes = R.drawable.pan, title = "Banco Pan"),
    BTG(iconRes = R.drawable.btg, title = "BTG Pactual"),
}