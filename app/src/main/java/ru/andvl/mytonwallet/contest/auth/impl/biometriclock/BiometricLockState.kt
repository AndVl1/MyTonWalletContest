package ru.andvl.mytonwallet.contest.auth.impl.biometriclock

import ru.andvl.mytonwallet.contest.arch.State

sealed interface BiometricLockState : State {
    data object Init : BiometricLockState
    data object Loading : BiometricLockState
    data object Loaded : BiometricLockState
}