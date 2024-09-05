package ru.andvl.mytonwallet.contest.bottombar.impl.wallet

sealed interface WalletNavigationEvent {
    data object NavigateToSend : WalletNavigationEvent
}