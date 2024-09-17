package ru.andvl.mytonwallet.contest.auth.impl.walletimport.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.auth.impl.walletimport.WalletImportAction
import ru.andvl.mytonwallet.contest.auth.impl.walletimport.WalletImportState
import ru.andvl.mytonwallet.contest.blockchain.util.SUGGESTION_WORDS_COUNT
import ru.andvl.mytonwallet.contest.ui.components.TonWalletButton
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun WalletImportScreenContent(
    state: WalletImportState,
    onAction: (WalletImportAction) -> Unit,
    modifier: Modifier = Modifier,
    scrollState: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    val focusManager = LocalFocusManager.current

    LazyColumn(
        state = scrollState,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { focusManager.clearFocus() }
            )
    ) {
        item {
            WalletImportScreenHeader(state.inputWords.size)
            Spacer(modifier = Modifier.height(32.dp))
        }
        itemsIndexed(
            items = state.inputWords,
            key = { index, _ -> index }
        ) { index, word ->
            val suggestions = state.mnemonicWords
                .filter {
                    it.lowercase().startsWith(word.lowercase())
                }
                .take(SUGGESTION_WORDS_COUNT)

            ImportWordTextField(
                index = index + 1,
                value = word,
                onValueChange = {
                    onAction(WalletImportAction.OnWordUpdated(index, it))
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = if (index < state.inputWords.size - 1) ImeAction.Next else ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Next) },
                    onDone = {
                        focusManager.clearFocus()
                        onAction(WalletImportAction.OnContinueClicked)
                    }
                ),
                suggestions = suggestions,
                onSuggestionClick = {
                    onAction(WalletImportAction.OnWordUpdated(index, it))
                    if (index < state.inputWords.size - 1) {
                        focusManager.moveFocus(FocusDirection.Next)
                    } else {
                        focusManager.clearFocus()
                        onAction(WalletImportAction.OnContinueClicked)
                    }
                },
                isError = suggestions.isEmpty(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
            TonWalletButton(
                text = stringResource(R.string.auth_wallet_import_continue),
                onClick = { onAction(WalletImportAction.OnContinueClicked) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(48.dp))
        }
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