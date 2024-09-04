package ru.andvl.mytonwallet.contest.auth.impl.walletimport

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.andvl.mytonwallet.contest.blockchain.api.BlockchainRepository

class BlockchainViewModel(
    private val blockchainRepository: BlockchainRepository
) : ViewModel() {
    private val _state = MutableStateFlow(emptyList<String>())
    val state = _state.asStateFlow()

    fun onClicked() {
        viewModelScope.launch {
            val result = blockchainRepository.getMnemonicWordList()
            Log.d("BlockchainViewModel", "result: $result")
            _state.update { result }
        }
    }
}