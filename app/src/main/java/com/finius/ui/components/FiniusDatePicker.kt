package com.finius.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.commandiron.wheel_picker_compose.WheelDatePicker
import com.finius.ui.theme.FiniusTheme
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toKotlinLocalDate

@Composable
fun FiniusDatePicker(
    modifier: Modifier = Modifier,
    onSelectDate: (LocalDate) -> Unit
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            WheelDatePicker(
                modifier = Modifier.fillMaxWidth(),
                onSnappedDate = { date -> onSelectDate(date.toKotlinLocalDate()) }
            )
        }
    }
}

@Preview
@Composable
private fun FiniusDatePickerPreview() {
    FiniusTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            FiniusDatePicker(
                onSelectDate = {}
            )
        }
    }
}