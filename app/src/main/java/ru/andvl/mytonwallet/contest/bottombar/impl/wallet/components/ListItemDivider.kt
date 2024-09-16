package ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.ui.theme.ListDividerColor
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun ListItemDivider(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(start = 76.dp)
) {
    HorizontalDivider(
        thickness = 0.5.dp,
        color = ListDividerColor,
        modifier = Modifier
            .padding(paddingValues)
            .then(modifier)
    )
}

@Preview
@Composable
fun ListItemDividerPreview() {
    MyTonWalletContestTheme {
        ListItemDivider()
    }
}