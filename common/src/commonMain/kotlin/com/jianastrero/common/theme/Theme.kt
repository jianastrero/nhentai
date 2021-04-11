package com.jianastrero.common.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.jianastrero.common.color.*

private val lightColors by lazy {
    lightColors(
        background = Light,
        error = Red800,
        primary = Amaranth500,
        primaryVariant = Amaranth800,
        secondary = Yellow500,
        secondaryVariant = Yellow800,
        surface = Light,
        onPrimary = Color.White,
        onSecondary = Color.Black,
        onBackground = Color.Black,
        onSurface = Color.Black,
        onError = Color.White
    )
}

private val darkColors = darkColors(
    background = Dark,
    error = Red300,
    primary = Amaranth500,
    primaryVariant = Amaranth300,
    secondary = Yellow300,
    secondaryVariant = Yellow800,
    surface = Dark,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = Color.Black
)

@Composable
fun NHentaiTheme(isDarkMode: Boolean = false, content: @Composable () -> Unit) {
    MaterialTheme(
        colors = if (isDarkMode) darkColors else lightColors
    ) {
        content()
    }
}