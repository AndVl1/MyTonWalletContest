package ru.andvl.mytonwallet.contest.auth.impl.recoverytest

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle

@Composable
fun CreateRecoveryTestScreen(
    navigateToHome: () -> Unit,
    navigateBack: () -> Unit,
    viewModel: RecoveryTestViewModel
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val navigationEvents = viewModel.navigationEvents

    LaunchedEffect(lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            navigationEvents.collect { navigationEvent ->
                when (navigationEvent) {
                    is RecoveryTestNavigationEvent.NavigateBack -> navigateBack()
                    is RecoveryTestNavigationEvent.NavigateToHome -> navigateToHome()
                }
            }
        }
    }

    RecoveryTestScreen(
        state = viewModel.state.collectAsState().value,
        onAction = viewModel::obtainEvent
    )
}