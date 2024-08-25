package ru.andvl.mytonwallet.contest.auth.impl.confirmpasscode

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import ru.andvl.mytonwallet.contest.auth.impl.passcode.PasscodeLength

@Composable
fun CreateConfirmPasscodeScreen(
    correctPasscode: String,
    passcodeLength: PasscodeLength,
    navigateBack: () -> Unit,
    navigateToBiometricLock: () -> Unit,
    viewModel: ConfirmPasscodeViewModel = koinViewModel(
        parameters = { parametersOf(correctPasscode, passcodeLength) }
    )
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val navigationEvents = viewModel.navigationEvents

    LaunchedEffect(lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            navigationEvents.collect { navigationEvent ->
                when (navigationEvent) {
                    is ConfirmPasscodeNavigationEvent.NavigateBack -> navigateBack()
                    is ConfirmPasscodeNavigationEvent.NavigateToBiometricLock -> navigateToBiometricLock()
                }
            }
        }
    }

    ConfirmPasscodeScreen(
        state = viewModel.state.collectAsState().value,
        onAction = viewModel::obtainEvent
    )
}