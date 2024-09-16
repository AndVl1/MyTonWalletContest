package ru.andvl.mytonwallet.contest.auth.impl.passcode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.toImmutableList
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.auth.impl.passcode.components.PasscodeKeyboard
import ru.andvl.mytonwallet.contest.auth.impl.passcode.components.PasscodeLockTitleWithDescription
import ru.andvl.mytonwallet.contest.ui.components.DotIndicatorsRow
import ru.andvl.mytonwallet.contest.ui.components.ErrorShakeBox
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun PasscodeScreen(
    state: PasscodeState,
    onAction: (PasscodeAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        contentWindowInsets = WindowInsets(0.dp)
    ) {
        Surface(
            modifier = modifier.padding(it),
            color = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_lock),
                    contentDescription = null,
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.height(72.dp))
                DotIndicatorsRow(
                    dotsNumber = state.correctPasscodeLength,
                    isSelected = { it < state.inputPasscode.length },
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(modifier = Modifier.height(72.dp))
                ErrorShakeBox(
                    triggerError = state.isPasswordIncorrect,
                    onErrorHandled = { onAction(PasscodeAction.ResetErrorState) }
                ) {
                    PasscodeLockTitleWithDescription()
                }
                Spacer(modifier = Modifier.height(40.dp))
                PasscodeKeyboard(
                    buttons = state.keyboardButtons.let { buttons ->
                        if (!state.isFingerprintAvailable) {
                            buttons.map { row ->
                                row.filter {
                                    it != PasscodeButtonItem.ActionButton(
                                        type = KeyboardActionType.FINGERPRINT,
                                        icon = R.drawable.ic_fingerprint,
                                    )
                                }.toImmutableList()
                            }.toImmutableList()
                        } else {
                            buttons
                        }
                    },
                    onClick = { onAction(PasscodeAction.OnPasscodeButtonClicked(it)) },
                    modifier = Modifier.fillMaxWidth(0.7f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PasscodeScreenPreview() {
    MyTonWalletContestTheme {
        PasscodeScreen(
            state = PasscodeState(
                inputPasscode = "12"
            ),
            onAction = {}
        )
    }
}