package ru.andvl.mytonwallet.contest.auth.impl.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun WalletCreatedFlowTitleWithDescription(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        WalletCreatedFlowTitle(title)
        Spacer(modifier = Modifier.height(12.dp))
        WalletCreatedFlowDescription(description)
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun SetPasscodeTitleWithDescriptionPreview() {
    MyTonWalletContestTheme {
        WalletCreatedFlowTitleWithDescription(
            title = stringResource(R.string.auth_set_passcode_screen_title),
            description = stringResource(
                R.string.auth_set_passcode_screen_description,
                4
            )
        )
    }
}