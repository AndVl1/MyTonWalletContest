package ru.andvl.mytonwallet.contest.auth.impl.recoverylist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle

@Composable
fun CreateRecoveryListScreen(
    navigateToRecoveryTest: (List<String>) -> Unit,
    navigateBack: () -> Unit,
    viewModel: RecoveryListViewModel
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val navigationEvents = viewModel.navigationEvents

    LaunchedEffect(lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            navigationEvents.collect { navigationEvent ->
                when (navigationEvent) {
                    is RecoveryListNavigationEvent.NavigateBack -> navigateBack()
                    is RecoveryListNavigationEvent.NavigateToRecoveryTest -> navigateToRecoveryTest(
                        navigationEvent.recoveryWords
                    )
                }
            }
        }
    }

    RecoveryListScreen(
        state = viewModel.state.collectAsState().value,
        onAction = viewModel::obtainEvent
    )
}