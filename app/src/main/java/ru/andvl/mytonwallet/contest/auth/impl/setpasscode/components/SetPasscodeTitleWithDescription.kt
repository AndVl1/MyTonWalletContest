package ru.andvl.mytonwallet.contest.auth.impl.setpasscode.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun SetPasscodeTitleWithDescription(
    passcodeLength: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.auth_set_passcode_screen_title),
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(
                R.string.auth_set_passcode_screen_description,
                passcodeLength
            ),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun SetPasscodeTitleWithDescriptionPreview() {
    MyTonWalletContestTheme {
        SetPasscodeTitleWithDescription(
            passcodeLength = 4
        )
    }
}