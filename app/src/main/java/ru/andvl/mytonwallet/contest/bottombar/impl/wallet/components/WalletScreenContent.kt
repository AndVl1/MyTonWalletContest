package ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
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
    scrollState: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        state = scrollState,
        contentPadding = contentPadding,
        modifier = modifier.fillMaxSize()
    ) {
        item {
            WalletScreenHeader(state.balance)
        }
        item {
            HorizontalDivider(
                thickness = 12.dp,
                color = MaterialTheme.colorScheme.tertiaryContainer
            )
        }
        item {
            if (state.assetTokens.isEmpty()) {
                WalletNoTransactions(modifier = Modifier.fillMaxSize())
            } else {
                WalletAssets()
            }
        }
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
