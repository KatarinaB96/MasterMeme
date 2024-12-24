package com.campus.mastermeme.core.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Primary,
    onPrimary = OnPrimaryFixed,
    primaryContainer = PrimaryContainer,
    onPrimaryContainer = OnSurface,
    secondary = SecondaryFixedDim,
    secondaryContainer = SurfaceContainerLow,
    surface = SurfaceContainer,
    onSurface = OnSurface,
    outline = SchemesOutline,
    error = SchemesError,
    background = DarkSurfaceContainerLowest,
)


@Composable
fun MasterMemeTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
