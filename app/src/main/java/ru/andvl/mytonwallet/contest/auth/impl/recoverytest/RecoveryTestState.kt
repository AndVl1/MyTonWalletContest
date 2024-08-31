package ru.andvl.mytonwallet.contest.auth.impl.recoverytest

import ru.andvl.mytonwallet.contest.arch.State

data class RecoveryTestState(
    val checkIndexes: List<Int> = emptyList(),
    val wordsWithIndexes: Map<Int, String> = emptyMap(),
    val isContinueButtonEnabled: Boolean = false
) : State
