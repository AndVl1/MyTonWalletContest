package ru.andvl.mytonwallet.contest.auth.impl.nowallet

import ru.andvl.mytonwallet.contest.arch.State

sealed interface NoWalletState : State {
    data object Init : NoWalletState
    data object Loading : NoWalletState
}