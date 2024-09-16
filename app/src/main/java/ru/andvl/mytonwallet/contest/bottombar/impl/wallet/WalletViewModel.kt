package ru.andvl.mytonwallet.contest.bottombar.impl.wallet

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
            getWalletBalance()
            fetchAllActivitySlice()
            getAssetTokens()
        }
    }

    override fun obtainEvent(event: WalletAction) {
        viewModelScope.launch {
            when (event) {
                is WalletAction.OnSendClicked -> {
                    _navigationEvents.emit(WalletNavigationEvent.NavigateToSend)
                }

                is WalletAction.OnTransactionClicked -> {
                    _state.update {
                        it.copy(
                            showTransactionDetails = true,
                            currentTransaction = event.activity
                        )
                    }
                }

                is WalletAction.OnTransactionDetailsDismiss -> {
                    _state.update { it.copy(showTransactionDetails = false) }
                }

                else -> {
                    /*TODO*/
                }
            }
        }
    }

    private suspend fun getWalletBalance() {
        try {
            withContext(Dispatchers.Main) {
                val balance = blockchainRepository.getCurrentAccountWalletBalance()
                _state.update { it.copy(balance = balance) }
            }
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
        }
    }

    private suspend fun getAssetTokens() {
        try {
            withContext(Dispatchers.Main) {
                blockchainRepository.getCurrentAccountAssetTokens().collect { assetTokens ->
                    Log.d("getAssetTokens", assetTokens.toString())
                    _state.update { it.copy(assetTokens = assetTokens) }
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
        }
    }

    private suspend fun fetchAllActivitySlice() {
        try {
            withContext(Dispatchers.Main) {
                blockchainRepository.fetchAllActivitySlice(50).apply {
                    val groupedActivities = this.groupBy { it.dateTime.date }

                    _state.update { currentState ->
                        currentState.copy(historyActivities = groupedActivities)
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
        }
    }

    companion object {
        const val TAG = "WalletViewModel"
    }
}