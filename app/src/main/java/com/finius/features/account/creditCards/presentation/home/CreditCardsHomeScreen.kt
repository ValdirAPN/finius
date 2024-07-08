package com.finius.features.account.creditCards.presentation.home

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.finius.R
import com.finius.core.domain.Account
import com.finius.features.account.creditCards.presentation.form.name.CardNameScreen
import com.finius.ui.components.FiniusButton
import com.finius.ui.components.FiniusButtonSize
import com.finius.ui.components.FiniusButtonVariant
import com.finius.ui.components.FiniusListItem
import com.finius.ui.components.FiniusNavigationBar
import com.finius.ui.components.FiniusNavigationBarLeadingAction

class CreditCardsHomeScreen : Screen {
    @Composable
    override fun Content() {

        val screenModel = rememberScreenModel<CardsHomeScreenModel>()
        val state by screenModel.uiState.collectAsStateWithLifecycle()

        val navigator = LocalNavigator.currentOrThrow

        val cardsHomeStrings = strings.creditCardStrings.homeStrings

        LaunchedEffect(Unit) {
            screenModel.assemble()
        }

        CreditCardsHomeScreenContent(
            strings = cardsHomeStrings,
            cards = state.cards,
            onClickNavigationIcon = { navigator.popUntilRoot() },
            onClickNewCard = { navigator.push(CardNameScreen()) }
        )
    }

}

@Composable
fun CreditCardsHomeScreenContent(
    strings: CreditCardsHomeStrings,
    cards: List<Account>,
    onClickNavigationIcon: () -> Unit,
    onClickNewCard: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier,
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
                    onClick = onClickNewCard,
                    trailingIconRes = R.drawable.plus_light,
                    modifier = Modifier.fillMaxWidth(),
                    variant = FiniusButtonVariant.PrimaryGhost,
                    size = FiniusButtonSize.Medium
                )

                if (cards.isEmpty()) {
                    Column(
                        modifier = Modifier.weight(1f).fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = strings.noCardsFound)
                    }
                } else {
                    LazyColumn {
                        items(items = cards, key = { card -> card.id }) { card ->
                            FiniusListItem(label = card.name, leadingContent = {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(100))
                                        .background(MaterialTheme.colorScheme.onBackground)
                                        .padding(4.dp)
                                ) {
                                    Icon(
                                        modifier = Modifier.size(28.dp),
                                        painter = painterResource(id = card.brand.iconRes),
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
}
