package ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.bottombar.impl.wallet.WalletAction
import ru.andvl.mytonwallet.contest.bottombar.impl.wallet.WalletState
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun WalletScreenContent(
    state: WalletState,
    onAction: (WalletAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        WalletAssets(state.assetTokens)
        HorizontalDivider(
            thickness = 12.dp,
            color = MaterialTheme.colorScheme.tertiaryContainer
        )
        WalletTransactionHistory()
    }
}

@Preview(showBackground = true)
@Composable
fun WalletScreenContentPreview() {
    MyTonWalletContestTheme {
        WalletScreenContent(
            state = WalletState(),
            onAction = {},
            modifier = Modifier.padding(vertical = 16.dp)
        )
    }
}
