package ru.andvl.mytonwallet.contest.auth.impl.walletimport

import ru.andvl.mytonwallet.contest.arch.State

data class WalletImportState(
    val words: List<String> = List(24) { "" },
    val showErrorDialog: Boolean = false
) : State {
    val isValid: Boolean
        get() = words.all { it.isNotEmpty() }
}

