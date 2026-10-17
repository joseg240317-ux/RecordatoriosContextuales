package com.example.recordatorioscontextuales.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val AppColors = lightColorScheme(
    primary = Color(0xFF2447C6),
    secondary = Color(0xFF536DFE),
    background = Color(0xFFF7F8FC),
    surface = Color.White,
    onPrimary = Color.White,
    onBackground = Color(0xFF171A21),
    onSurface = Color(0xFF171A21)
)

@Composable
fun RecordatoriosTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = AppColors,
        typography = MaterialTheme.typography,
        content = content
    )
}
