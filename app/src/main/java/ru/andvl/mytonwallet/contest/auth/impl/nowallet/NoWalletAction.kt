package ru.andvl.mytonwallet.contest.auth.impl.nowallet

import ru.andvl.mytonwallet.contest.arch.Action

sealed interface NoWalletAction : Action {
    data object OnCreateClicked : NoWalletAction
    data object OnImportClicked : NoWalletAction
}