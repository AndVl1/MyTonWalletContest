package ru.andvl.mytonwallet.contest.auth.impl.walletimport.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun MnemonicSuggestionMenu(
    words: List<String>,
    onSuggestionClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(6.dp)

    Row(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = shape
            )
            .border(
                width = 0.5.dp,
                color = MaterialTheme.colorScheme.onBackground.copy(
                    alpha = 0.1f
                ),
                shape = shape
            )
            .clip(shape)
            .horizontalScroll(rememberScrollState())
            .shadow(
                elevation = 2.dp,
                shape = shape,
                clip = false,
                spotColor = MaterialTheme.colorScheme.onBackground.copy(
                    alpha = 0.1f
                )
            )
    ) {
        words.forEach { suggestion ->
            MnemonicSuggestion(
                word = suggestion,
                onClick = { onSuggestionClick(suggestion) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MnemonicSuggestionMenuPreview() {
    MyTonWalletContestTheme {
        MnemonicSuggestionMenu(
            words = listOf("word", "work", "work", "work"),
            onSuggestionClick = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}