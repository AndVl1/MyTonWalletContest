package ru.andvl.mytonwallet.contest.auth.impl.recoverytest

import ru.andvl.mytonwallet.contest.arch.Action

sealed interface RecoveryTestAction : Action {
    data class WordEntered(val index: Int, val word: String) : RecoveryTestAction
    object ContinueClicked : RecoveryTestAction
}
