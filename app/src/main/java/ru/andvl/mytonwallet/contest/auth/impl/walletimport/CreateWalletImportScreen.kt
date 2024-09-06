package ru.andvl.mytonwallet.contest.auth.impl.walletimport

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle

@Composable
fun CreateWalletImportScreen(
    navigateToSetPasscode: (mnemonic: List<String>) -> Unit,
    navigateBack: () -> Unit,
    viewModel: WalletImportViewModel
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val navigationEvents = viewModel.navigationEvents

    LaunchedEffect(lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            navigationEvents.collect { navigationEvent ->
                when (navigationEvent) {
                    is WalletImportNavigationEvent.NavigateBack -> navigateBack()
                    is WalletImportNavigationEvent.NavigateToSetPasscode -> navigateToSetPasscode(
                        navigationEvent.mnemonic
                    )
                }
            }
        }
    }

    WalletImportScreen(
        state = viewModel.state.collectAsState().value,
        onAction = viewModel::obtainEvent
    )
}