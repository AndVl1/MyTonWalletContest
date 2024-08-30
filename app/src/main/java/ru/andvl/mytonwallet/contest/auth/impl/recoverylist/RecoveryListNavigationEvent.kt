package ru.andvl.mytonwallet.contest.auth.impl.recoverylist

sealed interface RecoveryListNavigationEvent {
    data object NavigateToRecoveryTest : RecoveryListNavigationEvent
}
