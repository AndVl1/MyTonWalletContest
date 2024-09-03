package ru.andvl.mytonwallet.contest.auth.impl.walletimport

import ru.andvl.mytonwallet.contest.arch.State

data class WalletImportState(
    val mnemonicWords: List<String> = listOf("word", "world", "work", "wolf", "woman"),
    val inputWords: List<String> = List(24) { "" },
    val showErrorDialog: Boolean = false
) : State

