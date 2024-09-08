package ru.andvl.mytonwallet.contest.auth.impl.confirmpasscode

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.auth.impl.confirmpasscode.components.ConfirmPasscodeScreenContent
import ru.andvl.mytonwallet.contest.auth.impl.passcode.PasscodeLength
import ru.andvl.mytonwallet.contest.auth.impl.ui.WalletCreatedFlowTopBar
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmPasscodeScreen(
    state: ConfirmPasscodeState,
    onAction: (ConfirmPasscodeAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            WalletCreatedFlowTopBar(
                onBackClicked = { onAction(ConfirmPasscodeAction.NavigateBack) },
            )
        },
        contentWindowInsets = WindowInsets(0.dp),
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