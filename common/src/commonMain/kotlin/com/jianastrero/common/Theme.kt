package com.jianastrero.common

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val lightColors = lightColors(
    background = Dark,
    error = Red300,
    primary = Amaranth500,
    primaryVariant = Amaranth800,
    secondary = Yellow300,
    surface = Dark,
    onPrimary = Color.Black,
    onSecondary = Color.Black
)

private val darkColors = darkColors(
    background = Dark,
    error = Red300,
    primary = Amaranth500,
    primaryVariant = Amaranth800,
    secondary = Yellow300,
    surface = Dark,
    onPrimary = Color.Black,
    onSecondary = Color.Black
)

@Composable
fun NHentaiTheme(isDarkMode: Boolean = true, content: @Composable () -> Unit) {
    MaterialTheme(
        colors = if (isDarkMode) darkColors else lightColors
    ) {
        content()
    }
}