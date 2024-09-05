package ru.andvl.mytonwallet.contest.bottombar.impl.wallet

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components.WalletScreenContent
import ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components.WalletScreenTopBar

@Composable
fun WalletScreen(
    state: WalletState,
    onAction: (WalletAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberLazyListState()

    Scaffold(
        topBar = {
            WalletScreenTopBar(
                scrollState = scrollState,
                onSettingsClicked = { onAction(WalletAction.OnSettingsClicked) },
                onScanClicked = { onAction(WalletAction.OnScanClicked) }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        WalletScreenContent(
            state = state,
            onAction = onAction,
            scrollState = scrollState,
            contentPadding = innerPadding,
            modifier = Modifier.fillMaxSize()
        )
    }
}