package ru.andvl.mytonwallet.contest.auth.impl.setpasscode

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateSetPasscodeScreen(
    navigateBack: () -> Unit,
    navigateToConfirmPasscode: () -> Unit,
    viewModel: SetPasscodeViewModel = koinViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val navigationEvents = viewModel.navigationEvents

    LaunchedEffect(lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            navigationEvents.collect { navigationEvent ->
                when (navigationEvent) {
                    is SetPasscodeNavigationEvent.NavigateBack -> navigateBack()
                    is SetPasscodeNavigationEvent.NavigateToConfirmPasscode -> navigateToConfirmPasscode()
                }
            }
        }
    }

    SetPasscodeScreen(
        state = viewModel.state.collectAsState().value,
        onAction = viewModel::obtainEvent
    )
}