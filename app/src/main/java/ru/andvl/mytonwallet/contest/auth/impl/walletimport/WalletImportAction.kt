package ru.andvl.mytonwallet.contest.auth.impl.walletimport

import ru.andvl.mytonwallet.contest.arch.Action

sealed interface WalletImportAction : Action {
    data object TESTNavigate : WalletImportAction // TODO delete
    data object NavigateBack : WalletImportAction
    data class OnWordUpdated(val index: Int, val word: String) : WalletImportAction
    data object OnWrongPhraseDismiss : WalletImportAction
    data object OnContinueClicked : WalletImportAction
}