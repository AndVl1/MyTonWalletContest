package ru.andvl.mytonwallet.contest.bottombar.impl.wallet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
    val scrollState = rememberScrollState()
    val maxOffset = with(LocalDensity.current) { 54.dp.toPx() }

    val scrollOffset = min(scrollState.value.toFloat(), maxOffset)
    val scrollProgress = scrollOffset / maxOffset

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
                onViewInExplorerClicked = { onAction(WalletAction.OnViewInExplorerClicked) })
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
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
            if (state.assetTokens.isEmpty()) {
                WalletNoTransactions(modifier = Modifier.weight(1f))
            } else {
                WalletScreenContent(
                    state = state,
                    onAction = onAction,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
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