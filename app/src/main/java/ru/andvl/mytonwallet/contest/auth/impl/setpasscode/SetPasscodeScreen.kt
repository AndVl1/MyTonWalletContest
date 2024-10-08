package ru.andvl.mytonwallet.contest.auth.impl.setpasscode

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.auth.impl.setpasscode.components.SetPasscodeScreenContent
import ru.andvl.mytonwallet.contest.auth.impl.ui.WalletCreatedFlowTopBar
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@OptIn(ExperimentalMaterial3Api::class)
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
        contentWindowInsets = WindowInsets(0.dp),
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
    showBackground = true,
    showSystemUi = true
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