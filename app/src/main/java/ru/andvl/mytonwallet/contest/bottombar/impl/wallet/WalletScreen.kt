package ru.andvl.mytonwallet.contest.bottombar.impl.wallet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components.TransactionBottomSheet
import ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components.WalletActionsRow
import ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components.WalletNoTransactions
import ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components.WalletScreenContent
import ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components.WalletScreenTitleWithBalance
import ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components.WalletScreenTopBar
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme
import ru.andvl.mytonwallet.contest.utils.USD_SYMBOL
import kotlin.math.min

@Composable
fun WalletScreen(
    state: WalletState,
    onAction: (WalletAction) -> Unit
) {
    val scrollState = rememberLazyListState()
    val maxOffset = with(LocalDensity.current) { 54.dp.toPx() }

    val scrollOffset by remember {
        derivedStateOf {
            min(
                if (scrollState.firstVisibleItemIndex == 0) {
                    scrollState.firstVisibleItemScrollOffset.toFloat()
                } else {
                    maxOffset
                },
                maxOffset
            )
        }
    }

    val scrollProgress by remember {
        derivedStateOf {
            scrollOffset / maxOffset
        }
    }

    Scaffold(
        topBar = {
            WalletScreenTopBar(
                balance = state.balance.toFloat(),
                currencySymbol = USD_SYMBOL,
                scrollProgress = scrollProgress,
                onSettingsClicked = { onAction(WalletAction.OnSettingsClicked) },
                onScanClicked = { onAction(WalletAction.OnScanClicked) }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        if (state.showTransactionDetails && state.currentTransaction != null) {
            TransactionBottomSheet(
                activity = state.currentTransaction,
                onDismissRequest = { onAction(WalletAction.OnTransactionDetailsDismiss) },
                onViewInExplorerClicked = { onAction(WalletAction.OnViewInExplorerClicked) }
            )
        }

        if (state.assetTokens.isEmpty()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                WalletScreenHeader(
                    state = state,
                    onAction = onAction,
                    scrollProgress = scrollProgress,
                    modifier = Modifier.fillMaxWidth()
                )
                WalletNoTransactions(modifier = Modifier.weight(1f))
            }
        } else {
            WalletScreenContent(
                state = state,
                onAction = onAction,
                lazyListState = scrollState,
                scrollProgress = scrollProgress,
                contentPadding = innerPadding,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun WalletScreenHeader(
    state: WalletState,
    onAction: (WalletAction) -> Unit,
    scrollProgress: Float,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        WalletScreenTitleWithBalance(
            balance = state.balance.toFloat(),
            currencySymbol = USD_SYMBOL,
            scrollProgress = scrollProgress
        )
        Spacer(modifier = Modifier.height(16.dp))
        WalletActionsRow(
            onAddButtonClicked = { onAction(WalletAction.OnAddClicked) },
            onSendButtonClicked = { onAction(WalletAction.OnSendClicked) },
            onEarnButtonClicked = { onAction(WalletAction.OnEarnClicked) },
            onSwapButtonClicked = { onAction(WalletAction.OnSwapClicked) },
            modifier = Modifier.padding(16.dp)
        )
        HorizontalDivider(
            thickness = 12.dp,
            color = MaterialTheme.colorScheme.tertiaryContainer
        )
    }
}


@Preview
@Composable
fun WalletScreenPreview() {
    MyTonWalletContestTheme {
        WalletScreen(
            state = WalletState(),
            onAction = {}
        )
    }
}