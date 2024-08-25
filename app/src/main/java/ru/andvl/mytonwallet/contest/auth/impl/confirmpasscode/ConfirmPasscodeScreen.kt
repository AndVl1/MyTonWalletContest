package ru.andvl.mytonwallet.contest.auth.impl.confirmpasscode

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.andvl.mytonwallet.contest.auth.impl.confirmpasscode.components.ConfirmPasscodeScreenContent
import ru.andvl.mytonwallet.contest.auth.impl.passcode.PasscodeLength
import ru.andvl.mytonwallet.contest.auth.impl.setpasscode.SetPasscodeScreenTopBar
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun ConfirmPasscodeScreen(
    state: ConfirmPasscodeState,
    onAction: (ConfirmPasscodeAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            SetPasscodeScreenTopBar(
                onBackClicked = { onAction(ConfirmPasscodeAction.NavigateBack) }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        ConfirmPasscodeScreenContent(
            state = state,
            onAction = onAction,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun SetPasswordScreenPreview() {
    MyTonWalletContestTheme {
        ConfirmPasscodeScreen(
            state = ConfirmPasscodeState(PasscodeLength.FOUR),
            onAction = {}
        )
    }
}