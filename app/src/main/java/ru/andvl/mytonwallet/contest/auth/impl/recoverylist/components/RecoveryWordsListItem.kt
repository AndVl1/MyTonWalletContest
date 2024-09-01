package ru.andvl.mytonwallet.contest.auth.impl.recoverylist.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun RecoveryWordsListItem(
    number: Int,
    word: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = number.toString(),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.tertiary,
            textAlign = TextAlign.End,
            modifier = Modifier.width(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = word,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun RecoveryWordsListItemPreview() {
    MyTonWalletContestTheme {
        RecoveryWordsListItem(
            number = 1,
            word = "keep",
            modifier = Modifier.fillMaxWidth()
        )
    }
}