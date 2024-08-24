package ru.andvl.mytonwallet.contest.auth.impl.nowallet

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.andvl.mytonwallet.contest.arch.BaseViewModel

class NoWalletViewModel : BaseViewModel<NoWalletAction, NoWalletState>() {
    private val _state = MutableStateFlow<NoWalletState>(NoWalletState.Init)

    override val state: StateFlow<NoWalletState> = _state.asStateFlow()

    private val _navigationEvents = MutableSharedFlow<NoWalletNavigationEvent>()
    val navigationEvents = _navigationEvents.asSharedFlow()

    override fun obtainEvent(event: NoWalletAction) {
        viewModelScope.launch {
            when (event) {
                is NoWalletAction.OnCreateClicked -> {
                    _state.update { NoWalletState.Loading }
                    createNewWallet()
                    _state.update { NoWalletState.Init }
                    _navigationEvents.emit(NoWalletNavigationEvent.NavigateToCreate)
                }

                is NoWalletAction.OnImportClicked -> {
                    _navigationEvents.emit(NoWalletNavigationEvent.NavigateToImport)
                }
            }
        }
    }

    private suspend fun createNewWallet() {
        delay(3000L)
    }
}