package ru.andvl.mytonwallet.contest.auth.impl.biometriclock

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
import ru.andvl.mytonwallet.contest.datastore.UserSettingsRepository

class BiometricLockViewModel(
    private val passcode: String,
    private val mnemonic: List<String>? = null,
    private val isImport: Boolean,
    private val blockchainRepository: BlockchainRepository,
    private val userSettingsRepository: UserSettingsRepository,
) : BaseViewModel<BiometricLockAction, BiometricLockState>() {
    private val _state = MutableStateFlow<BiometricLockState>(BiometricLockState.Init)

    override val state: StateFlow<BiometricLockState> = _state.asStateFlow()

    private val _navigationEvents = MutableSharedFlow<BiometricLockNavigationEvent>()
    val navigationEvents = _navigationEvents.asSharedFlow()

    override fun obtainEvent(event: BiometricLockAction) {
        viewModelScope.launch {
            when (event) {
                is BiometricLockAction.OnEnableClicked -> {
                    userSettingsRepository.updateAuthByFingerPrint(true)
                    navigateNext()
                }

                is BiometricLockAction.OnSkipClicked -> {
                    navigateNext()
                }
            }
        }
    }

    private suspend fun navigateNext() {
        if (isImport && mnemonic != null) {
            _state.update { BiometricLockState.Loading }
            try {
                withContext(Dispatchers.Main) {
                    blockchainRepository.createAccount(mnemonic, passcode, true)
                }
                _navigationEvents.emit(BiometricLockNavigationEvent.NavigateNext)
            } catch (e: Exception) {
                Log.e(TAG, e.message, e)
            }
            _state.update { BiometricLockState.Loaded }
        } else {
            _navigationEvents.emit(BiometricLockNavigationEvent.NavigateNext)
        }
    }

    companion object {
        const val TAG = "BiometricLockViewModel"
    }
}