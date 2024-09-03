package ru.andvl.mytonwallet.contest.auth.impl.walletimport.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.auth.impl.walletimport.WalletImportAction
import ru.andvl.mytonwallet.contest.auth.impl.walletimport.WalletImportState
import ru.andvl.mytonwallet.contest.ui.components.TonWalletButton
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun WalletImportScreenContent(
    state: WalletImportState,
    onAction: (WalletImportAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        WalletImportScreenHeader(state.words.size)
        Spacer(modifier = Modifier.height(24.dp))
//        ImportWordsInputSection(
//            words = state.words,
//            onValueChange = { index, word ->
//                onAction(WalletImportAction.OnWordUpdated(index, word))
//            },
//            onDone = {},
//            modifier = Modifier.fillMaxWidth()
//        )
        Spacer(modifier = Modifier.height(32.dp))
        TonWalletButton(
            text = stringResource(R.string.auth_wallet_import_continue),
            onClick = { onAction(WalletImportAction.OnContinueClicked) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RecoveryTestScreenContentPreview() {
    MyTonWalletContestTheme {
        WalletImportScreenContent(
            state = WalletImportState(),
            onAction = {},
            modifier = Modifier.padding(horizontal = 48.dp)
        )
    }
}