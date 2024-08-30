package ru.andvl.mytonwallet.contest.auth.impl.recoverylist

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.andvl.mytonwallet.contest.arch.BaseViewModel

class RecoveryListViewModel : BaseViewModel<RecoveryListAction, RecoveryListState>() {
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
                    _navigationEvents.emit(RecoveryListNavigationEvent.NavigateToRecoveryTest)
                }

                RecoveryListAction.NavigateBack -> {
                    _navigationEvents.emit(RecoveryListNavigationEvent.NavigateBack)
                }
            }
        }
    }

    private suspend fun getRecoveryWords() {
        // TODO add implementation with api repository
        delay(1000L)
        val words = listOf(
            "keep",
            "secret",
            "word",
            "keep",
            "secret",
            "word",
            "keep",
            "secret",
            "word",
            "keep",
            "secret",
            "word",
            "keep",
            "secret",
            "word",
            "keep",
            "secret",
            "word",
            "keep",
            "secret",
            "word",
            "keep",
            "secret",
            "word",
        )

        _state.update { it.copy(recoveryWords = words) }
    }
}
