package ru.andvl.mytonwallet.contest.auth.impl.walletimport

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.andvl.mytonwallet.contest.arch.BaseViewModel

class WalletImportViewModel : BaseViewModel<WalletImportAction, WalletImportState>() {
    private val _state = MutableStateFlow(WalletImportState())
    override val state: StateFlow<WalletImportState> = _state.asStateFlow()

    private val _navigationEvents = MutableSharedFlow<WalletImportNavigationEvent>()
    val navigationEvents = _navigationEvents.asSharedFlow()

    override fun obtainEvent(event: WalletImportAction) {
        viewModelScope.launch {
            when (event) {
                is WalletImportAction.OnContinueClicked -> onContinueClicked()
                is WalletImportAction.NavigateBack -> {
                    _navigationEvents.emit(WalletImportNavigationEvent.NavigateBack)
                }

                is WalletImportAction.OnWordUpdated -> updateWord(event.index, event.word)
                is WalletImportAction.OnWrongPhraseDismiss -> {
                    _state.update { it.copy(showErrorDialog = false) }
                }
            }
        }
    }

    private fun updateWord(index: Int, word: String) {
        _state.update { state ->
            val updatedInputWords = state.inputWords.toMutableList().apply {
                this[index] = word
            }
            state.copy(inputWords = updatedInputWords)
        }
    }

    private suspend fun onContinueClicked() {
        val isChecked = checkWords()
        if (!isChecked) _state.update { it.copy(showErrorDialog = true) }
        else _navigationEvents.emit(WalletImportNavigationEvent.NavigateToSetPasscode)
    }

    private fun checkWords(): Boolean {
        val currentState = _state.value

        return currentState.inputWords.all {
            it.isNotEmpty() && it.isNotBlank() && it in currentState.mnemonicWords
        }
    }
}
