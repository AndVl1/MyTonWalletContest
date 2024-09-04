package ru.andvl.mytonwallet.contest.auth.impl.recoverylist

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

class RecoveryListViewModel(
    private val blockchainRepository: BlockchainRepository
) : BaseViewModel<RecoveryListAction, RecoveryListState>() {
    private val _state = MutableStateFlow(RecoveryListState())
    override val state: StateFlow<RecoveryListState> = _state.asStateFlow()

    private val _navigationEvents = MutableSharedFlow<RecoveryListNavigationEvent>()
    val navigationEvents = _navigationEvents.asSharedFlow()

    init {
        viewModelScope.launch {
            getRecoveryWords()
        }
    }

    override fun obtainEvent(event: RecoveryListAction) {
        viewModelScope.launch {
            when (event) {
                RecoveryListAction.DoneClicked -> {
                    _navigationEvents.emit(
                        RecoveryListNavigationEvent.NavigateToRecoveryTest(
                            recoveryWords = _state.value.recoveryWords
                        )
                    )
                }

                RecoveryListAction.NavigateBack -> {
                    _navigationEvents.emit(RecoveryListNavigationEvent.NavigateBack)
                }
            }
        }
    }

    private suspend fun getRecoveryWords() {
        val words = withContext(Dispatchers.Main) { blockchainRepository.generateMnemonic() }

        _state.update { it.copy(recoveryWords = words) }
    }
}
