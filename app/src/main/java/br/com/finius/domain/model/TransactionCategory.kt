package br.com.finius.domain.model

import br.com.finius.R

enum class TransactionCategory(val iconRes: Int, val color: Colors) {
    FOOD(R.drawable.fork_knife, Colors.Melon),
    TRANSPORTATION(R.drawable.car, Colors.Aquamarine),
    HOUSING(R.drawable.houseline, Colors.Celeste),
    EDUCATION(R.drawable.graduation_cap, Colors.LavenderPink),
    HEALTH(R.drawable.first_aid_kit, Colors.LightGreen),
    LEISURE(R.drawable.island, Colors.Mindaro),
    CLOTHING(R.drawable.coat_hanger, Colors.Apricot),
    ENTERTAINMENT(R.drawable.television_simple, Colors.PowderBlue),
    SHOPPING(R.drawable.shopping_bag, Colors.Mauve2),
    TAXES(R.drawable.coins, Colors.Mauve),
    DONATIONS(R.drawable.hand_coins, Colors.LightGreen2),
    TRAVEL(R.drawable.airplane, Colors.Aquamarine2),
    SALARY(R.drawable.currency_dollar_simple, Colors.LightGreen),
    INVESTMENTS(R.drawable.piggy_bank, Colors.CarnationPink),
    OTHERS(R.drawable.dots_three_outline, Colors.JordyBlue),
}

fun TransactionCategory.getExhibitionName() = when (this) {
    TransactionCategory.FOOD -> "Alimentação"
    TransactionCategory.TRANSPORTATION -> "Transporte"
    TransactionCategory.HOUSING -> "Moradia"
    TransactionCategory.EDUCATION -> "Educação"
    TransactionCategory.HEALTH -> "Saúde"
    TransactionCategory.LEISURE -> "Lazer"
    TransactionCategory.CLOTHING -> "Vestuário"
    TransactionCategory.ENTERTAINMENT -> "Entretenimento"
    TransactionCategory.SHOPPING -> "Compras"
    TransactionCategory.TAXES -> "Impostos"
    TransactionCategory.DONATIONS -> "Doações"
    TransactionCategory.TRAVEL -> "Viagem"
    TransactionCategory.OTHERS -> "Outros"
    TransactionCategory.SALARY -> "Salário"
    TransactionCategory.INVESTMENTS -> "Investimentos"
}