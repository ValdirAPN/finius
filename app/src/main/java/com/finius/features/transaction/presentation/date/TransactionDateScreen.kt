package com.finius.features.transaction.presentation.date

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.finius.R
import com.finius.features.transaction.presentation.TransactionFormScreenModel
import com.finius.features.transaction.presentation.amount.TransactionAmountScreen
import com.finius.ui.components.FiniusButton
import com.finius.ui.components.FiniusDatePicker
import com.finius.ui.components.FiniusNavigationBar
import com.finius.ui.components.FiniusNavigationBarLeadingAction
import com.finius.ui.theme.FiniusTheme
import kotlinx.datetime.LocalDate

class TransactionDateScreen : Screen {
    @Composable
    override fun Content() {

        val model = rememberScreenModel<TransactionFormScreenModel>()
        val state by model.uiState.collectAsStateWithLifecycle()

        val navigator = LocalNavigator.currentOrThrow
        val dateStrings = strings.transactionStrings.dateStrings

        TransactionDateScreenContent(
            strings = dateStrings,
            onSelectDate = model::setDate,
            shouldRepeat = state.shouldRepeat,
            onSetShouldRepeat = model::setShouldRepeat,
            recurrenceUnit = state.recurrenceUnit,
            onSetRecurrenceUnit = model::setRecurrenceUnit,
            recurrence = state.recurrence,
            onSetRecurrence = model::setRecurrence,
            onClickNavigationIcon = navigator::pop,
            onClickContinue = { navigator.push(TransactionAmountScreen()) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionDateScreenContent(
    strings: TransactionDateStrings,
    onSelectDate: (LocalDate) -> Unit,
    shouldRepeat: Boolean,
    onSetShouldRepeat: (Boolean) -> Unit,
    recurrenceUnit: TransactionRecurrenceUnit,
    onSetRecurrenceUnit: (TransactionRecurrenceUnit) -> Unit,
    recurrence: Int,
    onSetRecurrence: (Int) -> Unit,
    onClickNavigationIcon: () -> Unit,
    onClickContinue: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            Column(
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                FiniusNavigationBar(
                    title = strings.title,
                    leadingAction = FiniusNavigationBarLeadingAction.Back(action = onClickNavigationIcon)
                )

                Column(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    FiniusDatePicker(onSelectDate = onSelectDate)

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = strings.shouldRepeatLabel, modifier = Modifier.weight(1f))
                        Switch(checked = shouldRepeat, onCheckedChange = onSetShouldRepeat)
                    }

                    if (shouldRepeat) {

                        val recurrences = TransactionRecurrenceUnit.entries
                        var expanded by remember { mutableStateOf(false) }

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(text = strings.howToRepeatLabel, modifier = Modifier.weight(1f))
                            ExposedDropdownMenuBox(
                                expanded = expanded,
                                onExpandedChange = { expanded = it }
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    modifier = Modifier
                                        .menuAnchor()
                                        .clip(RoundedCornerShape(100))
                                        .background(MaterialTheme.colorScheme.primaryContainer)
                                        .padding(horizontal = 16.dp)
                                        .height(48.dp)
                                ) {
                                    Text(
                                        text = recurrenceUnit.displayName(),
                                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                                    )

                                    if (expanded) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.caret_up_fill),
                                            contentDescription = null,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    } else {
                                        Icon(
                                            painter = painterResource(id = R.drawable.caret_down_fill),
                                            contentDescription = null,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                }
                                ExposedDropdownMenu(
                                    modifier = Modifier.fillMaxWidth(),
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false }
                                ) {

                                    recurrences.forEach {
                                        DropdownMenuItem(
                                            text = {
                                                Text(
                                                    it.displayName(),
                                                    modifier = Modifier.fillMaxWidth(),
                                                    textAlign = TextAlign.Center
                                                )
                                            },
                                            onClick = { onSetRecurrenceUnit(it) }
                                        )
                                    }
                                }
                            }
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                        ) {
                            Text(
                                text = strings.howLongToRepeatLabel(recurrenceUnit),
                                modifier = Modifier.weight(1f)
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier
                                    .clip(RoundedCornerShape(100))
                                    .background(MaterialTheme.colorScheme.primaryContainer)
                                    .padding(horizontal = 8.dp)
                                    .height(48.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.minus_light),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(RoundedCornerShape(100))
                                        .clickable {
                                            if (recurrence > 1) onSetRecurrence(recurrence - 1)
                                        }
                                        .padding(4.dp)
                                )
                                Text(
                                    text = recurrence.toString(),
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.plus_light),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(RoundedCornerShape(100))
                                        .clickable { onSetRecurrence(recurrence + 1) }
                                        .padding(4.dp)
                                )
                            }
                        }
                    }
                }
            }

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                FiniusButton(
                    text = strings.btnLabel,
                    onClick = onClickContinue,
                    modifier = Modifier.fillMaxWidth(),
                    trailingIconRes = R.drawable.arrow_right_light,
                )
            }
        }
    }
}

@Preview
@Composable
private fun TransactionDateScreenContentPreview() {
    FiniusTheme {
        TransactionDateScreenContent(
            strings = strings.transactionStrings.dateStrings,
            onSelectDate = {},
            shouldRepeat = false,
            onSetShouldRepeat = {},
            recurrenceUnit = TransactionRecurrenceUnit.MONTHLY,
            onSetRecurrenceUnit = {},
            recurrence = 1,
            onSetRecurrence = {},
            onClickContinue = {},
            onClickNavigationIcon = {}
        )
    }
}