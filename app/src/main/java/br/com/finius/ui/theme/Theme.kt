package br.com.finius.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Onyx,
    onPrimary = AntiFlashWhite,
    background = OnyxDarker,
    onBackground = AntiFlashWhite,
    surface = Onyx,
    onSurface = AntiFlashWhite,
    onSurfaceVariant = FrenchGray,
)

private val LightColorScheme = lightColorScheme(
    primary = Onyx,
    onPrimary = AntiFlashWhite,
    background = AntiFlashWhite,
    onBackground = Onyx,
    surface = Color.White,
    onSurface = Onyx,
    onSurfaceVariant = FrenchGray,

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun FiniusTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}