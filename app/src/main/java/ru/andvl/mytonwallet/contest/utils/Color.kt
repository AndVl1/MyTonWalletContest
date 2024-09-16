package ru.andvl.mytonwallet.contest.utils

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

fun generateRandomColor(): Color {
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)

    return Color(red, green, blue)
}

fun generateVerticalGradient(baseColor: Color): Brush {
    val lighterColor = baseColor.copy(alpha = 0.7f)
    val darkerColor = baseColor.copy(alpha = 1.0f)

    return Brush.verticalGradient(listOf(lighterColor, darkerColor))
}