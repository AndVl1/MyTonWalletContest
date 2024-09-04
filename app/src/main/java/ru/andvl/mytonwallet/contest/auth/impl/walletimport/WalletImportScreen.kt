package ru.andvl.mytonwallet.contest.auth.impl.walletimport

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
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
                onBackClicked = { onAction(WalletImportAction.NavigateBack) }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        val focusManager = LocalFocusManager.current

        if (state.showErrorDialog) {
            WrongPhraseDialog(
                onCloseClicked = { onAction(WalletImportAction.OnWrongPhraseDismiss) },
                onDismissRequest = { onAction(WalletImportAction.OnWrongPhraseDismiss) }
            )
        }

        LazyColumn(
            state = scrollState,
            contentPadding = innerPadding,
            modifier = Modifier
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = { focusManager.clearFocus() }
                )
                .fillMaxSize()
                .padding(horizontal = 48.dp)
        ) {
            item {
                WalletImportScreenContent(
                    state = state,
                    onAction = onAction,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
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