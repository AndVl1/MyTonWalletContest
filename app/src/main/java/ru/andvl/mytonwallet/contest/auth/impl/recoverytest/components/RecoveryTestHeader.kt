package ru.andvl.mytonwallet.contest.auth.impl.recoverytest.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.auth.impl.ui.WalletCreatedFlowTitle
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun RecoveryTestHeader(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(R.drawable.test), // Replace with your icon
            contentDescription = null,
            modifier = Modifier.size(124.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        WalletCreatedFlowTitle(stringResource(R.string.auth_recovery_test_title))
        Spacer(modifier = Modifier.height(12.dp))
        RecoveryTestDescription(listOf(1, 2, 3))
    }
}

@Preview(showBackground = true)
@Composable
private fun RecoveryTestHeaderPreview() {
    MyTonWalletContestTheme {
        RecoveryTestHeader(modifier = Modifier.padding(16.dp))
    }
}