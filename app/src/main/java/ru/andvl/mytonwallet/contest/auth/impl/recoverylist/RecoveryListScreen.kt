package ru.andvl.mytonwallet.contest.auth.impl.recoverylist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        rememberTopAppBarState()
    )
    Scaffold(
        topBar = {
            WalletCreatedFlowTopBar(
                onBackClicked = { onAction(RecoveryListAction.NavigateBack) },
                scrollBehavior = scrollBehavior
            )
        },
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    ) { innerPadding ->
        RecoveryListScreenContent(
            state = state,
            onAction = onAction,
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 48.dp)
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