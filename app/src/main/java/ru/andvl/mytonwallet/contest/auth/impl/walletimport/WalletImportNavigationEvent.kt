package ru.andvl.mytonwallet.contest.auth.impl.walletimport

sealed interface WalletImportNavigationEvent {
    data object NavigateBack : WalletImportNavigationEvent
    data object NavigateToHome : WalletImportNavigationEvent
}
