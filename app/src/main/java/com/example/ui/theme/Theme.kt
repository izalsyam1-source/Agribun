package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    onPrimary = Color(0xFF00390E),
    primaryContainer = DarkPrimaryContainer,
    onPrimaryContainer = Color(0xFFA5D6A7),
    secondary = SoilBrownLight,
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFF553B32),
    onSecondaryContainer = Color(0xFFD7CCC8),
    tertiary = HarvestGold,
    onTertiary = Color(0xFF332000),
    background = DarkBackground,
    onBackground = DarkOnSurface,
    surface = DarkSurface,
    onSurface = DarkOnSurface,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = Color(0xFFC2C8BE),
    outline = Color(0xFF8C9388)
)

private val LightColorScheme = lightColorScheme(
    primary = ForestGreenPrimary,
    onPrimary = Color.White,
    primaryContainer = GreenContainerLight,
    onPrimaryContainer = ForestGreenDark,
    secondary = SoilBrown,
    onSecondary = Color.White,
    secondaryContainer = SoilBrownContainer,
    onSecondaryContainer = Color(0xFF3E2723),
    tertiary = HarvestGold,
    onTertiary = Color.Black,
    tertiaryContainer = HarvestGoldLight,
    onTertiaryContainer = Color(0xFF5A3B00),
    background = BackgroundAgrarian,
    onBackground = Color(0xFF191C19),
    surface = SurfaceLight,
    onSurface = Color(0xFF191C19),
    surfaceVariant = SurfaceVariantLight,
    onSurfaceVariant = Color(0xFF424940),
    outline = OutlineColor
)

@Composable
fun AgriBunTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
