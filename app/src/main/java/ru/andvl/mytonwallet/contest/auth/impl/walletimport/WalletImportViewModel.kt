package ru.andvl.mytonwallet.contest.auth.impl.walletimport

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.andvl.mytonwallet.contest.arch.BaseViewModel

class WalletImportViewModel : BaseViewModel<WalletImportAction, WalletImportState>() {
    private val _state = MutableStateFlow(WalletImportState())
    override val state: StateFlow<WalletImportState> = _state.asStateFlow()

    override fun obtainEvent(event: WalletImportAction) {
        when (event) {
            is WalletImportAction.OnContinueClicked -> TODO()
            else -> TODO()
        }
    }
}
