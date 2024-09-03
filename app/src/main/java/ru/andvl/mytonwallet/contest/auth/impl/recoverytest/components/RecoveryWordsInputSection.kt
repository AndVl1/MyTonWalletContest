package ru.andvl.mytonwallet.contest.auth.impl.recoverytest.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.auth.impl.ui.MnemonicTextField
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun RecoveryWordsInputSection(
    wordsWithIndexes: Map<Int, String>,
    onValueChange: (Int, String) -> Unit,
    onDone: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        val wordIndexes = wordsWithIndexes.keys
        wordIndexes.forEachIndexed { i, wordIndex ->
            MnemonicTextField(
                index = wordIndex + 1,
                value = wordsWithIndexes[wordIndex]!!,
                onValueChange = { onValueChange(wordIndex, it) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = if (i < wordIndexes.size - 1) ImeAction.Next else ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Next) },
                    onDone = {
                        focusManager.clearFocus()
                        onDone()
                    }
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecoveryWordsInputSectionPreview() {
    MyTonWalletContestTheme {
        RecoveryWordsInputSection(
            wordsWithIndexes = mapOf(
                1 to "abcd",
                15 to "a",
                20 to ""
            ),
            onValueChange = { _, _ -> },
            onDone = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}