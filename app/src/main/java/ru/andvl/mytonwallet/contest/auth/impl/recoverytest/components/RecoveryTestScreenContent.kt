package ru.andvl.mytonwallet.contest.auth.impl.recoverytest.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.auth.impl.recoverytest.RecoveryTestAction
import ru.andvl.mytonwallet.contest.auth.impl.recoverytest.RecoveryTestState
import ru.andvl.mytonwallet.contest.ui.components.TonWalletButton
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun RecoveryTestScreenContent(
    state: RecoveryTestState,
    onAction: (RecoveryTestAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        RecoveryTestScreenHeader(state.checkIndexes)
        Spacer(modifier = Modifier.height(24.dp))
        RecoveryWordsInputSection(
            wordsWithIndexes = state.wordsWithIndexes,
            onValueChange = { index, word ->
                onAction(RecoveryTestAction.OnWordUpdated(index, word))
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))
        TonWalletButton(
            text = stringResource(R.string.auth_recovery_test_continue),
            onClick = { onAction(RecoveryTestAction.OnContinueClicked) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RecoveryTestScreenContentPreview() {
    MyTonWalletContestTheme {
        RecoveryTestScreenContent(
            state = RecoveryTestState(
                wordsWithIndexes = mapOf(
                    1 to "abcd",
                    15 to "a",
                    20 to ""
                )
            ),
            onAction = {},
            modifier = Modifier.padding(horizontal = 48.dp)
        )
    }
}