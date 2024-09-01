package ru.andvl.mytonwallet.contest.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun TonWalletAlertDialogActionSection(
    dismissButtonText: String,
    onDismissButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    confirmButtonText: String? = null,
    onConfirmButtonClicked: (() -> Unit)? = null
) {
    Column(
        horizontalAlignment = Alignment.End,
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp)
        ) {
            if (confirmButtonText != null && onConfirmButtonClicked != null) {
                TonWalletAlertDialogActionButton(
                    text = confirmButtonText,
                    onClick = onConfirmButtonClicked
                )
            }
            TonWalletAlertDialogActionButton(
                text = dismissButtonText,
                onClick = onDismissButtonClicked
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TonWalletAlertDialogActionSectionPreview() {
    MyTonWalletContestTheme {
        TonWalletAlertDialogActionSection(
            confirmButtonText = stringResource(R.string.auth_recovery_test_wrong_words_show_phrase),
            onConfirmButtonClicked = {},
            dismissButtonText = stringResource(R.string.auth_recovery_test_wrong_words_try_again),
            onDismissButtonClicked = {}
        )
    }
}