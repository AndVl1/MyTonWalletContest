package ru.andvl.mytonwallet.contest.auth.impl.recoverytest

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RecoveryTestScreen(
    state: RecoveryTestState,
    onAction: (RecoveryTestAction) -> Unit,
    modifier: Modifier = Modifier
) {
//    Column(
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState())
//    ) {
//        RecoveryTestHeader()
//        Spacer(modifier = Modifier.height(32.dp))
//        RecoveryTestContent(state, onAction)
//        Spacer(modifier = Modifier.height(32.dp))
//        Button(
//            onClick = { onAction(RecoveryTestAction.ContinueClicked) },
//            enabled = state.isContinueButtonEnabled,
//            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
//        ) {
//            Text(text = stringResource(R.string.auth_recovery_test_continue))
//        }
//    }
}
