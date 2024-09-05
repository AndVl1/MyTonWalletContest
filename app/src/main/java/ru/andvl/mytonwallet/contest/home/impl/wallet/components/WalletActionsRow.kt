package ru.andvl.mytonwallet.contest.home.impl.wallet.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun WalletActionsRow(
    onAddButtonClicked: () -> Unit,
    onSendButtonClicked: () -> Unit,
    onEarnButtonClicked: () -> Unit,
    onSwapButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        WalletActionButton(
            icon = ImageVector.vectorResource(R.drawable.ic_add),
            title = stringResource(R.string.main_wallet_add),
            onClick = onAddButtonClicked,
            modifier = Modifier.weight(1f)
        )
        WalletActionButton(
            icon = ImageVector.vectorResource(R.drawable.ic_send),
            title = stringResource(R.string.main_wallet_send),
            onClick = onSendButtonClicked,
            modifier = Modifier.weight(1f)
        )
        WalletActionButton(
            icon = ImageVector.vectorResource(R.drawable.ic_earn),
            title = stringResource(R.string.main_wallet_earn),
            onClick = onEarnButtonClicked,
            modifier = Modifier.weight(1f)
        )
        WalletActionButton(
            icon = ImageVector.vectorResource(R.drawable.ic_swap),
            title = stringResource(R.string.main_wallet_swap),
            onClick = onSwapButtonClicked,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WalletActionsRowPreview() {
    MyTonWalletContestTheme {
        WalletActionsRow(
            onAddButtonClicked = {},
            onSendButtonClicked = {},
            onEarnButtonClicked = {},
            onSwapButtonClicked = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}