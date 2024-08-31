package ru.andvl.mytonwallet.contest.auth.impl.biometriclock

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.andvl.mytonwallet.contest.arch.BaseViewModel

class BiometricLockViewModel : BaseViewModel<BiometricLockAction, BiometricLockState>() {
    private val _state = MutableStateFlow<BiometricLockState>(BiometricLockState.Init)

    override val state: StateFlow<BiometricLockState> = _state.asStateFlow()

    private val _navigationEvents = MutableSharedFlow<BiometricLockNavigationEvent>()
    val navigationEvents = _navigationEvents.asSharedFlow()

    override fun obtainEvent(event: BiometricLockAction) {
        viewModelScope.launch {
            when (event) {
                is BiometricLockAction.OnEnableClicked -> {
                    // TODO enable biometric auth
                    _navigationEvents.emit(BiometricLockNavigationEvent.NavigateToRecoveryList)
                }

                is BiometricLockAction.OnSkipClicked -> {
                    _navigationEvents.emit(BiometricLockNavigationEvent.NavigateToRecoveryList)
                }
            }
        }
    }
}