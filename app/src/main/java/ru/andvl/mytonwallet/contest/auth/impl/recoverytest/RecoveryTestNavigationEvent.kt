package ru.andvl.mytonwallet.contest.auth.impl.recoverytest

sealed interface RecoveryTestNavigationEvent {
    data object NavigateBack : RecoveryTestNavigationEvent
    data object NavigateToHome : RecoveryTestNavigationEvent
}
