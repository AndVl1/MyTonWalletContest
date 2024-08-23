package ru.andvl.mytonwallet.contest.auth.impl.passcode

sealed interface PasscodeNavigationEvent {
    data object NavigateToWallet : PasscodeNavigationEvent
}