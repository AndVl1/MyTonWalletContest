package ru.andvl.mytonwallet.contest.home.impl.wallet.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun WalletScreenContent(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        item {
            WalletScreenHeader()
        }
        item {
            HorizontalDivider(
                thickness = 12.dp,
                color = MaterialTheme.colorScheme.tertiaryContainer
            )
        }
        item {
            WalletNoTransactions(modifier = Modifier.fillMaxSize())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WalletScreenContentPreview() {
    MyTonWalletContestTheme {
        WalletScreenContent(
            modifier = Modifier.padding(vertical = 16.dp)
        )
    }
}
