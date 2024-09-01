package ru.andvl.mytonwallet.contest.auth.impl.recoverytest

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.auth.impl.recoverytest.components.RecoveryTestScreenContent
import ru.andvl.mytonwallet.contest.auth.impl.recoverytest.components.WrongWordsDialog
import ru.andvl.mytonwallet.contest.auth.impl.ui.WalletCreatedFlowTopBar
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecoveryTestScreen(
    state: RecoveryTestState,
    onAction: (RecoveryTestAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        rememberTopAppBarState()
    )
    Scaffold(
        topBar = {
            WalletCreatedFlowTopBar(
                onBackClicked = { onAction(RecoveryTestAction.NavigateBack) },
                scrollBehavior = scrollBehavior
            )
        },
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    ) { innerPadding ->
        val focusManager = LocalFocusManager.current

        if (state.isWrongWords) {
            WrongWordsDialog(
                onShowPhraseClicked = { onAction(RecoveryTestAction.NavigateBack) },
                onTryAgainClicked = { onAction(RecoveryTestAction.OnWrongWordsDismiss) },
                onDismissRequest = { onAction(RecoveryTestAction.OnWrongWordsDismiss) }
            )
        }

        RecoveryTestScreenContent(
            state = state,
            onAction = onAction,
            modifier = modifier
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = { focusManager.clearFocus() }
                )
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 48.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RecoveryTestScreenPreview() {
    MyTonWalletContestTheme {
        RecoveryTestScreen(
            state = RecoveryTestState(
                isWrongWords = true,
                wordsWithIndexes = mapOf(
                    1 to "abcd",
                    15 to "a",
                    20 to ""
                )
            ),
            onAction = {}
        )
    }
}
