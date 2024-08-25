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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.auth.impl.setpasscode.SetPasscodeState
import ru.andvl.mytonwallet.contest.ui.components.ErrorShakeBox
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun SetPasscodeScreenHeader(
    state: SetPasscodeState,
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

        val triggerError =
            if (state is SetPasscodeState.Confirm) state.isPasscodeIncorrect else false
        ErrorShakeBox(
            triggerError = triggerError,
            onErrorHandled = resetErrorState
        ) {
            SetPasscodeAnimatedTitleWithDescription(state)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SetPasscodeScreenHeaderPreview() {
    MyTonWalletContestTheme {
        SetPasscodeScreenHeader(
            state = SetPasscodeState.SetUp(),
            resetErrorState = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}