package ru.andvl.mytonwallet.contest.auth.impl.recoverytest

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.andvl.mytonwallet.contest.arch.BaseViewModel
import ru.andvl.mytonwallet.contest.blockchain.api.BlockchainRepository

class RecoveryTestViewModel(
    private val recoveryWords: List<String>,
    private val blockchainRepository: BlockchainRepository
) : BaseViewModel<RecoveryTestAction, RecoveryTestState>() {
    private val _state = MutableStateFlow(RecoveryTestState())

    override val state: StateFlow<RecoveryTestState> = _state.asStateFlow()

    private val _navigationEvents = MutableSharedFlow<RecoveryTestNavigationEvent>()
    val navigationEvents = _navigationEvents.asSharedFlow()

    init {
        viewModelScope.launch {
            generateRecoveryCheckIndexes()
        }
    }

    override fun obtainEvent(event: RecoveryTestAction) {
        viewModelScope.launch {
            when (event) {
                is RecoveryTestAction.NavigateBack -> {
                    _navigationEvents.emit(RecoveryTestNavigationEvent.NavigateBack)
                }

                is RecoveryTestAction.OnWordUpdated -> updateWord(event.index, event.word)
                is RecoveryTestAction.OnWrongWordsDismiss -> {
                    _state.update { it.copy(isWrongWords = false) }
                }
                is RecoveryTestAction.OnContinueClicked -> onContinueClicked()
            }
        }
    }

    private fun updateWord(index: Int, word: String) {
        _state.update { state ->
            val updatedWordsWithIndexes = state.wordsWithIndexes.toMutableMap().apply {
                put(index, word)
            }

            state.copy(wordsWithIndexes = updatedWordsWithIndexes)
        }
    }

    private suspend fun onContinueClicked() {
        val isChecked = checkWords()
        if (!isChecked) _state.update { it.copy(isWrongWords = true) }
        else _navigationEvents.emit(RecoveryTestNavigationEvent.NavigateToHome)
    }

    private fun checkWords(): Boolean {
        val currentState = _state.value
        val wordsWithIndexes = currentState.wordsWithIndexes

        return wordsWithIndexes.all { (index, word) ->
            index in recoveryWords.indices && recoveryWords[index] == word
        }
    }

    private fun generateRecoveryCheckIndexes() {
        val indexes = blockchainRepository.getMnemonicCheckIndexes()
        _state.update {
            it.copy(
                checkIndexes = indexes,
                wordsWithIndexes = indexes.associateWith { "" }
            )
        }
    }
}
