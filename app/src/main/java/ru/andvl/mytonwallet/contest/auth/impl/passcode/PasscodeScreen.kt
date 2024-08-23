package ru.andvl.mytonwallet.contest.auth.impl.passcode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.auth.impl.passcode.components.AnimatedPasscodeErrorText
import ru.andvl.mytonwallet.contest.auth.impl.passcode.components.PasscodeKeyboard
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme
import ru.andvl.mytonwallet.contest.ui.theme.components.DotIndicatorsRow

@Composable
fun PasscodeScreen(
    state: PasscodeState,
    onAction: (PasscodeAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
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
            AnimatedPasscodeErrorText(
                triggerError = state.isPasswordIncorrect,
                onErrorHandled = { onAction(PasscodeAction.ResetErrorState) }
            )
            Spacer(modifier = Modifier.height(40.dp))
            PasscodeKeyboard(
                buttons = state.keyboardButtons,
                onClick = { onAction(PasscodeAction.OnPasscodeButtonClicked(it)) },
                modifier = Modifier.fillMaxWidth(0.7f)
            )
        }
    }
}

@Preview(
    showBackground = true
)
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