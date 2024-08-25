package ru.andvl.mytonwallet.contest.auth.impl.confirmpasscode.components

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.ui.components.ErrorShakeBox
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun ConfirmPasscodeScreenHeader(
    passcodeLength: Int,
    triggerError: Boolean,
    resetErrorState: () -> Unit,
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
        ErrorShakeBox(
            triggerError = triggerError,
            onErrorHandled = resetErrorState
        ) {
            ConfirmPasscodeTitleWithDescription(passcodeLength)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SetPasscodeScreenHeaderPreview() {
    MyTonWalletContestTheme {
        ConfirmPasscodeScreenHeader(
            passcodeLength = 4,
            triggerError = false,
            resetErrorState = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}