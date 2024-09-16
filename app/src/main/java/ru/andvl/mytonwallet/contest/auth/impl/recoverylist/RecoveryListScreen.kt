package ru.andvl.mytonwallet.contest.auth.impl.recoverylist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.auth.impl.recoverylist.components.RecoveryListScreenContent
import ru.andvl.mytonwallet.contest.auth.impl.ui.WalletCreatedFlowTopBar
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecoveryListScreen(
    state: RecoveryListState,
    onAction: (RecoveryListAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            WalletCreatedFlowTopBar(
                onBackClicked = { onAction(RecoveryListAction.NavigateBack) },
            )
        },
    ) { innerPadding ->
        RecoveryListScreenContent(
            state = state,
            onAction = onAction,
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 48.dp)
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun RecoveryListScreenPreview() {
    MyTonWalletContestTheme {
        RecoveryListScreen(
            state = RecoveryListState(),
            onAction = {}
        )
    }
}