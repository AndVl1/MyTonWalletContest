package ru.andvl.mytonwallet.contest.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun TonWalletAlertDialog(
    title: String,
    description: String,
    onDismissRequest: () -> Unit,
    dismissButtonText: String,
    onDismissButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    confirmButtonText: String? = null,
    onConfirmButtonClicked: (() -> Unit)? = null
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors().copy(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground,
            ),
            modifier = modifier
        ) {
            TonWalletAlertDialogHeader(
                title = title,
                description = description,
                modifier = Modifier.padding(
                    top = 24.dp,
                    start = 24.dp,
                    end = 24.dp
                )
            )
            TonWalletAlertDialogActionSection(
                confirmButtonText = confirmButtonText,
                onConfirmButtonClicked = onConfirmButtonClicked,
                dismissButtonText = dismissButtonText,
                onDismissButtonClicked = onDismissButtonClicked,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TonWalletAlertDialogPreview() {
    MyTonWalletContestTheme {
        TonWalletAlertDialog(
            title = stringResource(R.string.auth_recovery_test_wrong_words_title),
            description = stringResource(R.string.auth_recovery_test_wrong_words_description),
            onDismissRequest = {},
            confirmButtonText = stringResource(R.string.auth_recovery_test_wrong_words_show_phrase),
            onConfirmButtonClicked = {},
            dismissButtonText = stringResource(R.string.auth_recovery_test_wrong_words_try_again),
            onDismissButtonClicked = {}
        )
    }
}