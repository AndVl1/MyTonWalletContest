package ru.andvl.mytonwallet.contest.auth.impl.recoverylist.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun RecoveryWordsList(
    words: List<String>,
    modifier: Modifier = Modifier
) {
    val halfWordsNumber = words.size / 2

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        repeat(halfWordsNumber) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                RecoveryWordsListItem(
                    number = it + 1,
                    word = words[it],
                    modifier = Modifier.weight(1f)
                )
                RecoveryWordsListItem(
                    number = it + halfWordsNumber + 1,
                    word = words[it + halfWordsNumber],
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecoveryWordsListPreview() {
    MyTonWalletContestTheme {
        RecoveryWordsList(
            words = listOf(
                "keep",
                "secret",
                "word",
                "keep",
                "secret",
                "word",
                "keep",
                "secret",
                "word",
                "keep",
                "secret",
                "word",
                "keep",
                "secret",
                "word",
                "keep",
                "secret",
                "word",
                "keep",
                "secret",
                "word",
                "keep",
                "secret",
                "word",
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(48.dp)
        )
    }
}