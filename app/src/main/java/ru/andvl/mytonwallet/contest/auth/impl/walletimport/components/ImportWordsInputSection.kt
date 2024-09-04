package ru.andvl.mytonwallet.contest.auth.impl.walletimport.components

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
import androidx.compose.ui.util.fastForEachIndexed
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun ImportWordsInputSection(
    mnemonicWords: List<String>,
    inputWords: List<String>,
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
        inputWords.fastForEachIndexed { index, word ->
            val suggestions = mnemonicWords.filter { it.contains(word) }
            
            ImportWordTextField(
                index = index + 1,
                value = word,
                onValueChange = { onValueChange(index, it) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = if (index < inputWords.size - 1) ImeAction.Next else ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Next) },
                    onDone = {
                        focusManager.clearFocus()
                        onDone()
                    }
                ),
                suggestions = suggestions,
                isError = suggestions.isEmpty(),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ImportWordsInputSectionPreview() {
    MyTonWalletContestTheme {
        ImportWordsInputSection(
            mnemonicWords = listOf("word", "work", "will", "world"),
            inputWords = listOf(
                "abcd",
                "w",
                ""
            ),
            onValueChange = { _, _ -> },
            onDone = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}