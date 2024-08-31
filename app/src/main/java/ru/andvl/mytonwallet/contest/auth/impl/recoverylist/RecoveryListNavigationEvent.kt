package ru.andvl.mytonwallet.contest.auth.impl.recoverylist

sealed interface RecoveryListNavigationEvent {
    data object NavigateBack : RecoveryListNavigationEvent
    data class NavigateToRecoveryTest(
        val recoveryWords: List<String>
    ) : RecoveryListNavigationEvent
}
