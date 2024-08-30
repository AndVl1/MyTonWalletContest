package ru.andvl.mytonwallet.contest.auth.impl.recoverylist

import ru.andvl.mytonwallet.contest.arch.Action

sealed interface RecoveryListAction : Action {
    data object NavigateBack : RecoveryListAction
    data object DoneClicked : RecoveryListAction
}
