package ru.andvl.mytonwallet.contest.bottombar.impl.wallet

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.andvl.mytonwallet.contest.arch.BaseViewModel
import ru.andvl.mytonwallet.contest.blockchain.api.BlockchainRepository


class WalletViewModel(
    private val blockchainRepository: BlockchainRepository
) : BaseViewModel<WalletAction, WalletState>() {
    private val _state = MutableStateFlow(WalletState())
    override val state: StateFlow<WalletState> = _state.asStateFlow()

    private val _navigationEvents = MutableSharedFlow<WalletNavigationEvent>()
    val navigationEvents = _navigationEvents.asSharedFlow()

    init {
        viewModelScope.launch {
            getAssetTokens()
        }
    }

    override fun obtainEvent(event: WalletAction) {
        viewModelScope.launch {
            when (event) {
                is WalletAction.OnSendClicked -> {
                    _navigationEvents.emit(WalletNavigationEvent.NavigateToSend)
                }

                else -> {
                    /*TODO*/
                }
            }
        }
    }

    private suspend fun getAssetTokens() {
        val assetTokens = blockchainRepository.getCurrentAccountTokenBalances()
        _state.update { it.copy(assetTokens = assetTokens) }
    }
}