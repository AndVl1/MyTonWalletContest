package ru.andvl.mytonwallet.contest.auth.impl.walletimport.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.ui.components.TonWalletAlertDialog
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun WrongPhraseDialog(
    onCloseClicked: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    TonWalletAlertDialog(
        title = stringResource(R.string.auth_wallet_import_wrong_phrase_title),
        description = stringResource(R.string.auth_wallet_import_wrong_phrase_description),
        onDismissRequest = onDismissRequest,
        dismissButtonText = stringResource(R.string.auth_wallet_import_wrong_phrase_close),
        onDismissButtonClicked = onCloseClicked,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun WrongPhraseDialogPreview() {
    MyTonWalletContestTheme {
        WrongPhraseDialog(
            onCloseClicked = {},
            onDismissRequest = {},
        )
    }
}