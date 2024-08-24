package ru.andvl.mytonwallet.contest.auth.impl.nowallet

sealed interface NoWalletNavigationEvent {
    data object NavigateToCreate : NoWalletNavigationEvent
    data object NavigateToImport : NoWalletNavigationEvent
}