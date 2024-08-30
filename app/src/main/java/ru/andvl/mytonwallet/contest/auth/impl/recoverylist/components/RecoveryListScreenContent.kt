package ru.andvl.mytonwallet.contest.auth.impl.recoverylist.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.auth.impl.recoverylist.RecoveryListAction
import ru.andvl.mytonwallet.contest.auth.impl.recoverylist.RecoveryListState
import ru.andvl.mytonwallet.contest.ui.components.TonWalletButton
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun RecoveryListScreenContent(
    state: RecoveryListState,
    onAction: (RecoveryListAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        RecoveryListScreenHeader()
        Spacer(modifier = Modifier.height(32.dp))
        RecoveryWordsList(
            words = state.recoveryWords,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))
        TonWalletButton(
            text = stringResource(R.string.auth_recovery_list_done),
            onClick = { onAction(RecoveryListAction.DoneClicked) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun RecoveryListScreenContentPreview() {
    MyTonWalletContestTheme {
        RecoveryListScreenContent(
            state = RecoveryListState(),
            onAction = {},
            modifier = Modifier.padding(horizontal = 48.dp)
        )
    }
}