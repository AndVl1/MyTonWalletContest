package ru.andvl.mytonwallet.contest.auth.impl.setpasscode

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.andvl.mytonwallet.contest.arch.BaseViewModel
import ru.andvl.mytonwallet.contest.auth.impl.passcode.PasscodeLength

class SetPasscodeViewModel : BaseViewModel<SetPasscodeAction, SetPasscodeState>() {
    private val _state = MutableStateFlow<SetPasscodeState>(SetPasscodeState.SetUp())

    override val state = _state.asStateFlow()

    private val _navigationEvents = MutableSharedFlow<SetPasscodeNavigationEvent>()
    val navigationEvents = _navigationEvents.asSharedFlow()

    override fun obtainEvent(event: SetPasscodeAction) {
        viewModelScope.launch {
            val currentState = _state.value

            when (event) {
                is SetPasscodeAction.TogglePasscodeLength -> togglePasscodeLength(currentState)

                is SetPasscodeAction.OnNumberKeyboardButtonClicked -> onNumberKeyboardButtonClicked(
                    state = currentState,
                    button = event.button
                )

                is SetPasscodeAction.Confirm -> onConfirm(currentState)

                is SetPasscodeAction.NavigateBack -> {
                    _state.update { SetPasscodeState.SetUp() }
                    _navigationEvents.emit(SetPasscodeNavigationEvent.NavigateBack)
                }
            }
        }
    }

    private fun togglePasscodeLength(state: SetPasscodeState) {
        if (state is SetPasscodeState.SetUp) {
            val newLength = if (state.passcodeLength == PasscodeLength.FOUR)
                PasscodeLength.SIX else PasscodeLength.FOUR

            _state.update {
                state.copy(
                    passcodeLength = newLength,
                    inputPasscode = ""
                )
            }
        }
    }


    private fun updateInputPasscode(state: SetPasscodeState, digit: Int) {
        if (state.inputPasscode.length < state.passcodeLength.value) {
            val resultString = state.inputPasscode + digit

            _state.update {
                when (state) {
                    is SetPasscodeState.SetUp -> state.copy(inputPasscode = resultString)
                    is SetPasscodeState.Confirm -> state.copy(inputPasscode = resultString)
                }
            }

            if (state.inputPasscode.length + 1 == state.passcodeLength.value) {
                obtainEvent(SetPasscodeAction.Confirm)
            }
        }
    }

    private fun deleteLastDigit(state: SetPasscodeState) {
        val resultString = state.inputPasscode.dropLast(1)

        _state.update {
            when (state) {
                is SetPasscodeState.SetUp -> state.copy(inputPasscode = resultString)
                is SetPasscodeState.Confirm -> state.copy(inputPasscode = resultString)
            }
        }
    }

    private fun onNumberKeyboardButtonClicked(
        state: SetPasscodeState,
        button: NumberKeyboardButtonItem
    ) {
        when (button) {
            is NumberKeyboardButtonItem.DigitButton -> {
                updateInputPasscode(state, button.digitWithDescription.digit)
            }

            is NumberKeyboardButtonItem.ActionButton -> {
                when (button.type) {
                    NumberKeyboardActionType.DELETE -> {
                        deleteLastDigit(state)
                    }
                }
            }
        }
    }

    private suspend fun onConfirm(state: SetPasscodeState) {
        when (state) {
            is SetPasscodeState.SetUp -> {
                _state.update {
                    SetPasscodeState.Confirm(
                        passcodeLength = state.passcodeLength,
                        correctPasscode = state.inputPasscode
                    )
                }
            }

            is SetPasscodeState.Confirm -> {
                _navigationEvents.emit(
                    SetPasscodeNavigationEvent.NavigateToBiometricLock
                )
            }
        }
    }
}