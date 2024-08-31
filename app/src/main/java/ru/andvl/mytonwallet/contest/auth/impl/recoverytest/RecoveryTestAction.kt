package ru.andvl.mytonwallet.contest.auth.impl.recoverytest

import ru.andvl.mytonwallet.contest.arch.Action

sealed interface RecoveryTestAction : Action {
    data object NavigateBack : RecoveryTestAction
    data class OnWordUpdated(val index: Int, val word: String) : RecoveryTestAction
    data object OnContinueClicked : RecoveryTestAction
}
