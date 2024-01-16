package com.marchenaya.core.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val lightColorScheme = lightColorScheme(
    primary = com.marchenaya.core.ui.theme.md_theme_light_primary,
    onPrimary = com.marchenaya.core.ui.theme.md_theme_light_onPrimary,
    primaryContainer = com.marchenaya.core.ui.theme.md_theme_light_primaryContainer,
    onPrimaryContainer = com.marchenaya.core.ui.theme.md_theme_light_onPrimaryContainer,
    secondary = com.marchenaya.core.ui.theme.md_theme_light_secondary,
    onSecondary = com.marchenaya.core.ui.theme.md_theme_light_onSecondary,
    secondaryContainer = com.marchenaya.core.ui.theme.md_theme_light_secondaryContainer,
    onSecondaryContainer = com.marchenaya.core.ui.theme.md_theme_light_onSecondaryContainer,
    tertiary = com.marchenaya.core.ui.theme.md_theme_light_tertiary,
    onTertiary = com.marchenaya.core.ui.theme.md_theme_light_onTertiary,
    tertiaryContainer = com.marchenaya.core.ui.theme.md_theme_light_tertiaryContainer,
    onTertiaryContainer = com.marchenaya.core.ui.theme.md_theme_light_onTertiaryContainer,
    error = com.marchenaya.core.ui.theme.md_theme_light_error,
    errorContainer = com.marchenaya.core.ui.theme.md_theme_light_errorContainer,
    onError = com.marchenaya.core.ui.theme.md_theme_light_onError,
    onErrorContainer = com.marchenaya.core.ui.theme.md_theme_light_onErrorContainer,
    background = com.marchenaya.core.ui.theme.md_theme_light_background,
    onBackground = com.marchenaya.core.ui.theme.md_theme_light_onBackground,
    surface = com.marchenaya.core.ui.theme.md_theme_light_surface,
    onSurface = com.marchenaya.core.ui.theme.md_theme_light_onSurface,
    surfaceVariant = com.marchenaya.core.ui.theme.md_theme_light_surfaceVariant,
    onSurfaceVariant = com.marchenaya.core.ui.theme.md_theme_light_onSurfaceVariant,
    outline = com.marchenaya.core.ui.theme.md_theme_light_outline,
    inverseOnSurface = com.marchenaya.core.ui.theme.md_theme_light_inverseOnSurface,
    inverseSurface = com.marchenaya.core.ui.theme.md_theme_light_inverseSurface,
    inversePrimary = com.marchenaya.core.ui.theme.md_theme_light_inversePrimary,
    surfaceTint = com.marchenaya.core.ui.theme.md_theme_light_surfaceTint,
    outlineVariant = com.marchenaya.core.ui.theme.md_theme_light_outlineVariant,
    scrim = com.marchenaya.core.ui.theme.md_theme_light_scrim,
)


private val darkColorScheme = darkColorScheme(
    primary = com.marchenaya.core.ui.theme.md_theme_dark_primary,
    onPrimary = com.marchenaya.core.ui.theme.md_theme_dark_onPrimary,
    primaryContainer = com.marchenaya.core.ui.theme.md_theme_dark_primaryContainer,
    onPrimaryContainer = com.marchenaya.core.ui.theme.md_theme_dark_onPrimaryContainer,
    secondary = com.marchenaya.core.ui.theme.md_theme_dark_secondary,
    onSecondary = com.marchenaya.core.ui.theme.md_theme_dark_onSecondary,
    secondaryContainer = com.marchenaya.core.ui.theme.md_theme_dark_secondaryContainer,
    onSecondaryContainer = com.marchenaya.core.ui.theme.md_theme_dark_onSecondaryContainer,
    tertiary = com.marchenaya.core.ui.theme.md_theme_dark_tertiary,
    onTertiary = com.marchenaya.core.ui.theme.md_theme_dark_onTertiary,
    tertiaryContainer = com.marchenaya.core.ui.theme.md_theme_dark_tertiaryContainer,
    onTertiaryContainer = com.marchenaya.core.ui.theme.md_theme_dark_onTertiaryContainer,
    error = com.marchenaya.core.ui.theme.md_theme_dark_error,
    errorContainer = com.marchenaya.core.ui.theme.md_theme_dark_errorContainer,
    onError = com.marchenaya.core.ui.theme.md_theme_dark_onError,
    onErrorContainer = com.marchenaya.core.ui.theme.md_theme_dark_onErrorContainer,
    background = com.marchenaya.core.ui.theme.md_theme_dark_background,
    onBackground = com.marchenaya.core.ui.theme.md_theme_dark_onBackground,
    surface = com.marchenaya.core.ui.theme.md_theme_dark_surface,
    onSurface = com.marchenaya.core.ui.theme.md_theme_dark_onSurface,
    surfaceVariant = com.marchenaya.core.ui.theme.md_theme_dark_surfaceVariant,
    onSurfaceVariant = com.marchenaya.core.ui.theme.md_theme_dark_onSurfaceVariant,
    outline = com.marchenaya.core.ui.theme.md_theme_dark_outline,
    inverseOnSurface = com.marchenaya.core.ui.theme.md_theme_dark_inverseOnSurface,
    inverseSurface = com.marchenaya.core.ui.theme.md_theme_dark_inverseSurface,
    inversePrimary = com.marchenaya.core.ui.theme.md_theme_dark_inversePrimary,
    surfaceTint = com.marchenaya.core.ui.theme.md_theme_dark_surfaceTint,
    outlineVariant = com.marchenaya.core.ui.theme.md_theme_dark_outlineVariant,
    scrim = com.marchenaya.core.ui.theme.md_theme_dark_scrim,
)

@Composable
fun RecipeBookTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content
    )
}