package com.finius.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.finius.ui.theme.BlueGrey20
import com.finius.ui.theme.BlueGrey30
import com.finius.ui.theme.BlueGrey40

@Composable
fun FiniusDivider(modifier: Modifier = Modifier) {
    Spacer(modifier = modifier
        .fillMaxWidth()
        .height(1.dp)
        .background(BlueGrey20))
}