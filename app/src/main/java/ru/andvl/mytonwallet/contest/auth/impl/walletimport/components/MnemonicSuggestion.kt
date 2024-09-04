package ru.andvl.mytonwallet.contest.auth.impl.walletimport.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun MnemonicSuggestion(
    word: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = word,
        style = MaterialTheme.typography.bodyLarge,
        fontSize = 15.sp,
        modifier = modifier
            .clickable(
                role = Role.DropdownList,
                onClick = onClick
            )
            .padding(horizontal = 16.dp, vertical = 12.dp)
    )
}

@Preview
@Composable
private fun MnemonicSuggestionPreview() {
    MyTonWalletContestTheme {
        MnemonicSuggestion(
            word = "word",
            onClick = {},
        )
    }
}