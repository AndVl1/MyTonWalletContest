package ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun WalletScreenHeader(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        WalletScreenTitleWithBalance(
            balance = 12345.67f, // TODO
            currencySymbol = "\$"
        )
        Spacer(modifier = Modifier.height(16.dp))
        WalletActionsRow(
            onAddButtonClicked = { /*TODO*/ },
            onSendButtonClicked = { /*TODO*/ },
            onEarnButtonClicked = { /*TODO*/ },
            onSwapButtonClicked = { /*TODO*/ },
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WalletScreenHeaderPreview() {
    MyTonWalletContestTheme {
        WalletScreenHeader(modifier = Modifier.padding(16.dp))
    }
}