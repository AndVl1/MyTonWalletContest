package ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.bottombar.impl.wallet.WalletAction
import ru.andvl.mytonwallet.contest.bottombar.impl.wallet.WalletScreenHeader
import ru.andvl.mytonwallet.contest.bottombar.impl.wallet.WalletState
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun WalletScreenContent(
    state: WalletState,
    lazyListState: LazyListState,
    scrollProgress: Float,
    onAction: (WalletAction) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        state = lazyListState,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        contentPadding = contentPadding,
        modifier = modifier
    ) {
        item {
            WalletScreenHeader(
                state = state,
                onAction = onAction,
                scrollProgress = scrollProgress
            )
        }
        walletAssets(state.assetTokens)
        item {
            HorizontalDivider(
                thickness = 12.dp,
                color = MaterialTheme.colorScheme.tertiaryContainer
            )
        }
        walletTransactionHistory(
            history = state.historyActivities,
            onHistoryItemClicked = { onAction(WalletAction.OnTransactionClicked(it)) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WalletScreenContentPreview() {
    MyTonWalletContestTheme {
        WalletScreenContent(
            state = WalletState(),
            onAction = {},
            lazyListState = rememberLazyListState(),
            scrollProgress = 0f,
            modifier = Modifier.padding(vertical = 16.dp)
        )
    }
}
