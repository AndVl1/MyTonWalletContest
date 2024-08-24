package ru.andvl.mytonwallet.contest.auth.impl.setpasscode

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.auth.impl.setpasscode.components.NumberKeyboard
import ru.andvl.mytonwallet.contest.auth.impl.setpasscode.utils.vibrateOnKeyboardButtonClick
import ru.andvl.mytonwallet.contest.ui.components.ButtonStyle
import ru.andvl.mytonwallet.contest.ui.components.DotIndicatorsRow
import ru.andvl.mytonwallet.contest.ui.components.TonWalletButton
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun SetPasscodeScreen(
    state: SetPasscodeState,
    onAction: (SetPasscodeAction) -> Unit,
) {
    Scaffold(
        topBar = {
            SetPasscodeScreenTopBar(
                onBackClicked = { onAction(SetPasscodeAction.NavigateBack) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Image(
                    painter = painterResource(R.drawable.monkey_eyes_closed),
                    contentDescription = null,
                    modifier = Modifier.size(124.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = stringResource(R.string.auth_set_passcode_screen_title),
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(
                        R.string.auth_set_passcode_screen_description,
                        state.passcodeLength.value
                    ),
                    style = MaterialTheme.typography.bodyLarge
                )

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
                    state.passcodeLength.value
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
}

@Preview(
    showBackground = true
)
@Composable
private fun SetPasswordScreenPreview() {
    MyTonWalletContestTheme {
        SetPasscodeScreen(
            state = SetPasscodeState(),
            onAction = {}
        )
    }
}