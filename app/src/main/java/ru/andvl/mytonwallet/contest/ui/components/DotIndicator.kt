package ru.andvl.mytonwallet.contest.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun DotIndicator(
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    val animatedColor by animateColorAsState(
        if (isSelected) MaterialTheme.colorScheme.onPrimary else Color.Transparent,
        label = "color"
    )

    Box(
        modifier = modifier
            .size(16.dp)
            .drawBehind {
                drawCircle(animatedColor)
            }
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.36f),
                shape = CircleShape
            )
    )
}

@Preview
@Composable
fun DotIndicatorPreview() {
    MyTonWalletContestTheme {
        DotIndicator(isSelected = true)
    }
}