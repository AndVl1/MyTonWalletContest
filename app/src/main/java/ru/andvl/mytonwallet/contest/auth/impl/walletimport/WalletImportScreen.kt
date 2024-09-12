package ru.andvl.mytonwallet.contest.auth.impl.walletimport

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.auth.impl.walletimport.components.WalletImportScreenContent
import ru.andvl.mytonwallet.contest.auth.impl.walletimport.components.WalletImportTopBar
import ru.andvl.mytonwallet.contest.auth.impl.walletimport.components.WrongPhraseDialog
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun WalletImportScreen(
    state: WalletImportState,
    onAction: (WalletImportAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberLazyListState()

    Scaffold(
        topBar = {
            WalletImportTopBar(
                scrollState = scrollState,
                onBackClicked = { onAction(WalletImportAction.NavigateBack) },
                onAutofillAccountWithTransactionsClicked = { onAction(WalletImportAction.AutofillAccountWithTransactions) },
                onAutofillAccountWithoutTransactionsClicked = { onAction(WalletImportAction.AutofillAccountWithoutTransactions) },
            )
        },
        modifier = modifier
    ) { innerPadding ->
        if (state.showErrorDialog) {
            WrongPhraseDialog(
                onCloseClicked = { onAction(WalletImportAction.OnWrongPhraseDismiss) },
                onDismissRequest = { onAction(WalletImportAction.OnWrongPhraseDismiss) }
            )
        }

        WalletImportScreenContent(
            state = state,
            onAction = onAction,
            scrollState = scrollState,
            contentPadding = innerPadding,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 48.dp)
        )
        Spacer(modifier = Modifier.height(48.dp))
    }
}


@Preview(showBackground = true)
@Composable
private fun WalletImportScreenPreview() {
    MyTonWalletContestTheme {
        WalletImportScreen(
            state = WalletImportState(),
            onAction = {}
        )
    }
}