package ru.andvl.mytonwallet.contest.auth.impl.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun WalletCreatedFlowTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineMedium,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun WalletCreatedFlowTitlePreview() {
    MyTonWalletContestTheme {
        WalletCreatedFlowTitle("hello")
    }
}