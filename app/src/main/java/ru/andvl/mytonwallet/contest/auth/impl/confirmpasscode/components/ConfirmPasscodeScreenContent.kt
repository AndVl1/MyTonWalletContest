package ru.andvl.mytonwallet.contest.auth.impl.confirmpasscode.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.auth.impl.confirmpasscode.ConfirmPasscodeAction
import ru.andvl.mytonwallet.contest.auth.impl.confirmpasscode.ConfirmPasscodeState
import ru.andvl.mytonwallet.contest.auth.impl.passcode.PasscodeLength
import ru.andvl.mytonwallet.contest.auth.impl.setpasscode.components.NumberKeyboard
import ru.andvl.mytonwallet.contest.ui.components.DotIndicatorsRow
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme
import ru.andvl.mytonwallet.contest.utils.vibrateOnKeyboardButtonClick

@Composable
fun ConfirmPasscodeScreenContent(
    state: ConfirmPasscodeState,
    onAction: (ConfirmPasscodeAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            ConfirmPasscodeScreenHeader(
                passcodeLength = state.passcodeLength.value,
                triggerError = state.isPasscodeIncorrect,
                resetErrorState = { onAction(ConfirmPasscodeAction.ResetErrorState) }
            )
            Spacer(modifier = Modifier.height(24.dp))
            DotIndicatorsRow(
                dotsNumber = state.passcodeLength.value,
                isSelected = { it < state.inputPasscode.length },
                indicatorsColor = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(98.dp))

        val context = LocalContext.current
        NumberKeyboard(
            buttons = state.keyboardButtons,
            onClick = {
                vibrateOnKeyboardButtonClick(context)
                onAction(ConfirmPasscodeAction.OnNumberKeyboardButtonClicked(it))
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ConfirmPasscodeScreenContentPreview() {
    MyTonWalletContestTheme {
        ConfirmPasscodeScreenContent(
            state = ConfirmPasscodeState(PasscodeLength.FOUR),
            onAction = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}