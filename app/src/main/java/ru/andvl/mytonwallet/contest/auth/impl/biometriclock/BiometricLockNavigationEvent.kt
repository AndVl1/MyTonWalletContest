package ru.andvl.mytonwallet.contest.auth.impl.biometriclock

sealed interface BiometricLockNavigationEvent {
    data object NavigateNext : BiometricLockNavigationEvent
}