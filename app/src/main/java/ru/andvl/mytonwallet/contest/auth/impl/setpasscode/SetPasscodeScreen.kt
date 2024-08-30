package ru.andvl.mytonwallet.contest.auth.impl.setpasscode

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.andvl.mytonwallet.contest.auth.impl.setpasscode.components.SetPasscodeScreenContent
import ru.andvl.mytonwallet.contest.auth.impl.ui.WalletCreatedFlowTopBar
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun SetPasscodeScreen(
    state: SetPasscodeState,
    onAction: (SetPasscodeAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            WalletCreatedFlowTopBar(
                onBackClicked = { onAction(SetPasscodeAction.NavigateBack) }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        SetPasscodeScreenContent(
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
        SetPasscodeScreen(
            state = SetPasscodeState(),
            onAction = {}
        )
    }
}