package ru.andvl.mytonwallet.contest.auth.impl.biometriclock

import ru.andvl.mytonwallet.contest.arch.Action

sealed interface BiometricLockAction : Action {
    data object OnEnableClicked : BiometricLockAction
    data object OnSkipClicked : BiometricLockAction
}