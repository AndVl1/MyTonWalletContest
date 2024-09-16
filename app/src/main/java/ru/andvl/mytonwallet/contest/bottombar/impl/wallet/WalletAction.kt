package ru.andvl.mytonwallet.contest.bottombar.impl.wallet

import ru.andvl.mytonwallet.contest.arch.Action
import ru.andvl.mytonwallet.contest.bottombar.impl.model.HistoryActivity


sealed interface WalletAction : Action {
    data object OnSettingsClicked : WalletAction
    data object OnScanClicked : WalletAction
    data object OnAddClicked : WalletAction
    data object OnSendClicked : WalletAction
    data object OnEarnClicked : WalletAction
    data object OnSwapClicked : WalletAction
    data class OnTransactionClicked(val activity: HistoryActivity) : WalletAction
    data object OnTransactionDetailsDismiss : WalletAction
    data object OnViewInExplorerClicked : WalletAction
}