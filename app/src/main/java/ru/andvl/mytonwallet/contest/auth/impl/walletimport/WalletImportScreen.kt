package ru.andvl.mytonwallet.contest.auth.impl.walletimport

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.auth.impl.walletimport.components.WalletImportScreenContent
import ru.andvl.mytonwallet.contest.ui.components.TonWalletAlertDialog

@Composable
fun WalletImportScreen(
    state: WalletImportState,
    onAction: (WalletImportAction) -> Unit,
    modifier: Modifier = Modifier
) {
    if (state.showErrorDialog) {
        TonWalletAlertDialog(
            title = stringResource(R.string.auth_wallet_import_wrong_phrase_title),
            description = stringResource(R.string.auth_wallet_import_wrong_phrase_description),
            onDismissRequest = { onAction(WalletImportAction.OnWrongWordsDismiss) },
            dismissButtonText = stringResource(R.string.auth_wallet_import_wrong_phrase_close),
            onDismissButtonClicked = { onAction(WalletImportAction.OnWrongWordsDismiss) }
        )
    }

    WalletImportScreenContent(
        state = state,
        onAction = onAction
    )

//    Column(
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = modifier
//            .verticalScroll(rememberScrollState())
//            .padding(16.dp)
//    ) {
//        Text(
//            text = stringResource(id = R.string.auth_wallet_import_title),
//            style = MaterialTheme.typography.headlineMedium
//            ,
//            modifier = Modifier.padding(bottom = 8.dp)
//        )
//        Text(
//            text = stringResource(id = R.string.auth_wallet_import_description_start),
//            style = MaterialTheme.typography.bodyLarge,
//            color = MaterialTheme.colorScheme.onSurfaceVariant,
//            modifier = Modifier.padding(bottom = 16.dp)
//        )
//
//        // Mnemonic Fields
//        Column {
//            for (i in 1..24) {
//                RecoveryTextField(
//                    index = i,
//                    value = state.words[i - 1],
//                    onValueChange = { onAction(WalletImportAction.OnWordUpdated(i - 1, it)) },
//                    keyboardOptions = KeyboardOptions.Default.copy(
//                        imeAction = if (i == 24) ImeAction.Done else ImeAction.Next
//                    ),
//                    keyboardActions = KeyboardActions(onDone = {
//                        if (i == 24) {
//                            onAction(WalletImportAction.OnContinueClicked)
//                        }
//                    }),
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 4.dp)
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(24.dp))
//        TonWalletButton(
//            text = stringResource(id = R.string.auth_wallet_import_continue),
//            onClick = { onAction(WalletImportAction.OnContinueClicked) }
//        )
//    }
}

