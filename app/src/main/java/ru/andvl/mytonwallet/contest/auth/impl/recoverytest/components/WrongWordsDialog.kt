package ru.andvl.mytonwallet.contest.auth.impl.recoverytest.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.ui.components.TonWalletAlertDialog
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun WrongWordsDialog(
    onShowPhraseClicked: () -> Unit,
    onTryAgainClicked: () -> Unit,
    onDismissRequest: () -> Unit
) {
    TonWalletAlertDialog(
        title = stringResource(R.string.auth_recovery_test_wrong_words_title),
        description = stringResource(R.string.auth_recovery_test_wrong_words_description),
        onDismissRequest = onDismissRequest,
        confirmButtonText = stringResource(R.string.auth_recovery_test_wrong_words_show_phrase),
        onConfirmButtonClicked = onShowPhraseClicked,
        dismissButtonText = stringResource(R.string.auth_recovery_test_wrong_words_try_again),
        onDismissButtonClicked = onTryAgainClicked
    )
}

@Preview(showBackground = true)
@Composable
private fun WrongWordsDialogPreview() {
    MyTonWalletContestTheme {
        WrongWordsDialog(
            {}, {}, {}
        )
    }
}