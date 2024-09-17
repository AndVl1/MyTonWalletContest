package ru.andvl.mytonwallet.contest.auth.impl.setpasscode.components

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.auth.impl.passcode.PasscodeLength
import ru.andvl.mytonwallet.contest.auth.impl.setpasscode.SetPasscodeAction
import ru.andvl.mytonwallet.contest.auth.impl.setpasscode.SetPasscodeState
import ru.andvl.mytonwallet.contest.ui.components.ButtonStyle
import ru.andvl.mytonwallet.contest.ui.components.DotIndicatorsRow
import ru.andvl.mytonwallet.contest.ui.components.TonWalletButton
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme
import ru.andvl.mytonwallet.contest.utils.vibrateOnKeyboardButtonClick

@Composable
fun SetPasscodeScreenContent(
    state: SetPasscodeState,
    onAction: (SetPasscodeAction) -> Unit,
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
            SetPasscodeScreenHeader(state.passcodeLength.value)
            Spacer(modifier = Modifier.height(24.dp))
            DotIndicatorsRow(
                dotsNumber = state.passcodeLength.value,
                isSelected = { it < state.inputPasscode.length },
                indicatorsColor = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(8.dp)
            )
        }

        TonWalletButton(
            text = stringResource(
                R.string.auth_set_passcode_change_passcode_length,
                when (state.passcodeLength) {
                    PasscodeLength.FOUR -> PasscodeLength.SIX.value
                    PasscodeLength.SIX -> PasscodeLength.FOUR.value
                }
            ),
            buttonStyle = ButtonStyle.SECONDARY,
            onClick = {
                onAction(SetPasscodeAction.TogglePasscodeLength)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(48.dp))

        val context = LocalContext.current
        NumberKeyboard(
            buttons = state.keyboardButtons,
            onClick = {
                vibrateOnKeyboardButtonClick(context)
                onAction(SetPasscodeAction.OnNumberKeyboardButtonClicked(it))
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SetPasscodeScreenContentPreview() {
    MyTonWalletContestTheme {
        SetPasscodeScreenContent(
            state = SetPasscodeState(),
            onAction = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}