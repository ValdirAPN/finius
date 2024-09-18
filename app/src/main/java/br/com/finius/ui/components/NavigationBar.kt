package br.com.finius.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.finius.R

sealed class NavigationLeading(val iconRes: Int, open val action: () -> Unit) {
    data class Close(override val action: () -> Unit) :
        NavigationLeading(iconRes = R.drawable.x, action = action)

    data class Back(override val action: () -> Unit) :
        NavigationLeading(iconRes = R.drawable.arrow_left, action = action)
}

data class NavigationTrailing(val iconRes: Int, val action: () -> Unit)

@Composable
fun NavigationBar(
    label: String,
    leading: NavigationLeading,
    modifier: Modifier = Modifier,
    primaryAction: NavigationTrailing? = null,
    secondaryAction: NavigationTrailing? = null,
) {
    val labelPadding = if (secondaryAction != null) 104.dp else 56.dp
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
    ) {
        IconButton(onClick = leading.action, modifier = Modifier.align(Alignment.CenterStart)) {
            Icon(
                painter = painterResource(id = leading.iconRes),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = label,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = labelPadding),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
        )
        Row(modifier = Modifier.align(Alignment.CenterEnd)) {
            primaryAction?.let {
                IconButton(onClick = it.action) {
                    Icon(
                        painter = painterResource(id = it.iconRes),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            secondaryAction?.let {
                IconButton(onClick = it.action) {
                    Icon(
                        painter = painterResource(id = it.iconRes),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}