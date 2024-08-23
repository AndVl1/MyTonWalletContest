package ru.andvl.mytonwallet.contest.auth.impl.walletimport

import ru.andvl.mytonwallet.contest.arch.State

sealed interface WalletImportState : State {
    object Init : WalletImportState
    data class Words(
        // TODO придумать как сделать строже. 24 слова для импорта
        val words: List<String>
    ): WalletImportState
}
