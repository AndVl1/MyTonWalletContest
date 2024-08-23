package ru.andvl.mytonwallet.contest.auth.impl.passcode

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreatePasscodeScreen(
    navigateToWallet: () -> Unit,
    viewModel: PasscodeViewModel = koinViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val navigationEvents = viewModel.navigationEvents

    LaunchedEffect(lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            navigationEvents.collect { navigationEvent ->
                when (navigationEvent) {
                    is PasscodeNavigationEvent.NavigateToWallet -> navigateToWallet()
                }
            }
        }
    }

    PasscodeScreen(
        state = viewModel.state.collectAsState().value,
        onAction = viewModel::obtainEvent
    )
}