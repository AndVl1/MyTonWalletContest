package ru.andvl.mytonwallet.contest.auth.impl.walletimport.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.auth.impl.ui.WalletCreatedFlowTitle
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun WalletImportScreenHeader(
    wordsNumber: Int,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        WalletCreatedFlowTitle(stringResource(R.string.auth_wallet_import_title))
        Spacer(modifier = Modifier.height(12.dp))
        WalletImportDescription(wordsNumber)
    }
}

@Preview(showBackground = true)
@Composable
private fun RecoveryTestScreenHeaderPreview() {
    MyTonWalletContestTheme {
        WalletImportScreenHeader(
            wordsNumber = 24,
            modifier = Modifier.padding(16.dp)
        )
    }
}