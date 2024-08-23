package ru.andvl.mytonwallet.contest.auth.impl.walletimport

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ru.andvl.mytonwallet.contest.arch.BaseViewModel

class WalletImportViewModel : BaseViewModel<WalletImportAction, WalletImportState>() {
    private val _state = MutableStateFlow<WalletImportState>(WalletImportState.Init)

    override val state: StateFlow<WalletImportState>
        = _state.asStateFlow()

    override fun obtainEvent(event: WalletImportAction) {
        when (event) {
            is WalletImportAction.OnInputChanged -> TODO()
            is WalletImportAction.OnDoneClicked -> TODO()
        }
    }
}
