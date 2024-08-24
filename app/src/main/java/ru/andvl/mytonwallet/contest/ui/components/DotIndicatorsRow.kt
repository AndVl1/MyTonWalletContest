package ru.andvl.mytonwallet.contest.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun DotIndicatorsRow(
    dotsNumber: Int,
    isSelected: (Int) -> Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        repeat(dotsNumber) {
            DotIndicator(isSelected = isSelected(it))
        }
    }
}

@Preview
@Composable
fun DotIndicatorsRowPreview() {
    MyTonWalletContestTheme {
        DotIndicatorsRow(
            dotsNumber = 4,
            isSelected = { it < 2 },
            modifier = Modifier.padding(16.dp)
        )
    }
}