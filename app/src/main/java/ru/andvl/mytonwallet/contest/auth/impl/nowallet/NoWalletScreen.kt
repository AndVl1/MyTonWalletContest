package ru.andvl.mytonwallet.contest.auth.impl.nowallet

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.andvl.mytonwallet.contest.ui.components.Loading
import ru.andvl.mytonwallet.contest.ui.components.LoadingStyle
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun NoWalletScreen(
    state: NoWalletState,
    onAction: (NoWalletAction) -> Unit,
    modifier: Modifier = Modifier
) {
    if (state is NoWalletState.Loading) {
        Loading(
            modifier = modifier.fillMaxSize(),
            style = LoadingStyle.DIALOG
        )
    }

    NoWalletContent(
        onCreateClicked = { onAction(NoWalletAction.OnCreateClicked) },
        onImportClicked = { onAction(NoWalletAction.OnImportClicked) },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun NoWalletScreenPreview() {
    MyTonWalletContestTheme {
        NoWalletScreen(
            state = NoWalletState.Init,
            onAction = {},
        )
    }
}