package ru.andvl.mytonwallet.contest.auth.impl.walletimport

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

class WalletImportViewModel(
    private val blockchainRepository: BlockchainRepository
) : BaseViewModel<WalletImportAction, WalletImportState>() {
    private val _state = MutableStateFlow(WalletImportState())
    override val state: StateFlow<WalletImportState> = _state.asStateFlow()

    private val _navigationEvents = MutableSharedFlow<WalletImportNavigationEvent>()
    val navigationEvents = _navigationEvents.asSharedFlow()

    init {
        viewModelScope.launch {
            getMnemonicWordList()
        }
    }

    override fun obtainEvent(event: WalletImportAction) {
        viewModelScope.launch {
            when (event) {
                is WalletImportAction.TESTNavigate -> {
                    _state.update {
                        it.copy(
                            inputWords = listOf(
                                "lava", "life", "bacon", "either",
                                "chapter", "lunch", "soda", "syrup",
                                "attitude", "option", "embody", "vacant",
                                "coconut", "labor", "butter", "rescue",
                                "year", "civil", "never", "wave",
                                "monkey", "fog", "sun", "pattern",
                            )
                        )
                    }
                }
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

    private suspend fun getMnemonicWordList() {
        val words = withContext(Dispatchers.Main) {
            blockchainRepository.getMnemonicWordList()
        }
        _state.update { it.copy(mnemonicWords = words) }
    }


    private fun updateWord(index: Int, word: String) {
        _state.update { state ->
            val updatedInputWords = state.inputWords.toMutableList().apply {
                this[index] = word.lowercase()
            }
            state.copy(inputWords = updatedInputWords)
        }
    }

    private suspend fun onContinueClicked() {
        val isChecked = checkWords()
        if (!isChecked) _state.update { it.copy(showErrorDialog = true) }
        else _navigationEvents.emit(WalletImportNavigationEvent.NavigateToSetPasscode)
    }

    private suspend fun checkWords(): Boolean {
        val currentState = _state.value

        return currentState.inputWords.all {
            it.isNotEmpty() && it.isNotBlank() && it in currentState.mnemonicWords
        } && withContext(Dispatchers.Main) {
            blockchainRepository.validateMnemonic(currentState.inputWords)
        }
    }
}
