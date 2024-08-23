package ru.andvl.mytonwallet.contest.auth.impl.walletimport

import ru.andvl.mytonwallet.contest.arch.Action

sealed interface WalletImportAction : Action {
    data class OnInputChanged(val position: Int, val word: String) : WalletImportAction
    object OnDoneClicked : WalletImportAction
}
