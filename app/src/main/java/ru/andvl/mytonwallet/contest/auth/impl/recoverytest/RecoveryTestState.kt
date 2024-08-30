package ru.andvl.mytonwallet.contest.auth.impl.recoverytest

import ru.andvl.mytonwallet.contest.arch.State

data class RecoveryTestState(
    val recoveryWords: List<String>,
    val isContinueButtonEnabled: Boolean = false
) : State
