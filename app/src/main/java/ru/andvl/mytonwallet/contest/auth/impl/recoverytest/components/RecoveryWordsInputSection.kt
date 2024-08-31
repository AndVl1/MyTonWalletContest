package ru.andvl.mytonwallet.contest.auth.impl.recoverytest.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun RecoveryWordsInputSection(
    wordsWithIndexes: Map<Int, String>,
    onValueChange: (Int, String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        wordsWithIndexes.forEach { (index, word) ->
            RecoveryTextField(
                index = index + 1,
                value = word,
                onValueChange = { onValueChange(index, it) },
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
            modifier = Modifier.padding(16.dp)
        )
    }
}