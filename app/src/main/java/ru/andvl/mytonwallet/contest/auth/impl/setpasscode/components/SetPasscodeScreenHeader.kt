package ru.andvl.mytonwallet.contest.auth.impl.setpasscode.components

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
import ru.andvl.mytonwallet.contest.auth.impl.ui.WalletCreatedFlowTitleWithDescription
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun SetPasscodeScreenHeader(
    passcodeLength: Int,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(R.drawable.monkey_eyes_closed),
            contentDescription = null,
            modifier = Modifier.size(124.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        WalletCreatedFlowTitleWithDescription(
            title = stringResource(R.string.auth_set_passcode_screen_title),
            description = stringResource(
                R.string.auth_set_passcode_screen_description,
                passcodeLength
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SetPasscodeScreenHeaderPreview() {
    MyTonWalletContestTheme {
        SetPasscodeScreenHeader(
            passcodeLength = 4,
            modifier = Modifier.padding(16.dp)
        )
    }
}