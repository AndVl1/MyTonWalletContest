package ru.andvl.mytonwallet.contest.auth.impl.biometriclock

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle

@Composable
fun CreateBiometricLockScreen(
    navigateToRecoveryList: () -> Unit,
    viewModel: BiometricLockViewModel
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val navigationEvents = viewModel.navigationEvents

    LaunchedEffect(lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            navigationEvents.collect { navigationEvent ->
                when (navigationEvent) {
                    is BiometricLockNavigationEvent.NavigateToRecoveryList -> navigateToRecoveryList()
                }
            }
        }
    }

    BiometricLockScreen(
        onAction = viewModel::obtainEvent
    )
}