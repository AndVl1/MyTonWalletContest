package ru.andvl.mytonwallet.contest.auth.impl.confirmpasscode

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.andvl.mytonwallet.contest.arch.BaseViewModel
import ru.andvl.mytonwallet.contest.auth.impl.passcode.PasscodeLength
import ru.andvl.mytonwallet.contest.auth.impl.setpasscode.NumberKeyboardActionType
import ru.andvl.mytonwallet.contest.auth.impl.setpasscode.NumberKeyboardButtonItem

class ConfirmPasscodeViewModel(
    private val correctPasscode: String,
    passcodeLength: PasscodeLength,
) : BaseViewModel<ConfirmPasscodeAction, ConfirmPasscodeState>() {
    private val _state = MutableStateFlow(
        ConfirmPasscodeState(
            passcodeLength = passcodeLength
        )
    )

    override val state = _state.asStateFlow()

    private val _navigationEvents = MutableSharedFlow<ConfirmPasscodeNavigationEvent>()
    val navigationEvents = _navigationEvents.asSharedFlow()

    override fun obtainEvent(event: ConfirmPasscodeAction) {
        viewModelScope.launch {
            when (event) {
                is ConfirmPasscodeAction.OnNumberKeyboardButtonClicked -> onNumberKeyboardButtonClicked(
                    button = event.button
                )

                is ConfirmPasscodeAction.Confirm -> onPasscodeCheck()

                is ConfirmPasscodeAction.NavigateBack -> {
                    _navigationEvents.emit(ConfirmPasscodeNavigationEvent.NavigateBack)
                }

                is ConfirmPasscodeAction.ResetErrorState -> resetErrorState()
            }
        }
    }

    private fun updateInputPasscode(digit: Int) {
        if (_state.value.inputPasscode.length < _state.value.passcodeLength.value) {
            _state.update {
                it.copy(
                    inputPasscode = it.inputPasscode + digit
                )
            }

            _state.value.let {
                if (it.inputPasscode.length == it.passcodeLength.value) {
                    obtainEvent(ConfirmPasscodeAction.Confirm)
                }
            }
        }
    }

    private fun deleteLastDigit() {
        _state.update { it.copy(inputPasscode = it.inputPasscode.dropLast(1)) }
    }

    private fun onNumberKeyboardButtonClicked(button: NumberKeyboardButtonItem) {
        when (button) {
            is NumberKeyboardButtonItem.DigitButton -> {
                updateInputPasscode(button.digitWithDescription.digit)
            }

            is NumberKeyboardButtonItem.ActionButton -> {
                when (button.type) {
                    NumberKeyboardActionType.DELETE -> {
                        deleteLastDigit()
                    }
                }
            }
        }
    }

    private suspend fun onPasscodeCheck() {
        val isCorrect = _state.value.inputPasscode == correctPasscode
        _state.update { it.copy(isPasscodeIncorrect = !isCorrect) }

        if (isCorrect) {
            _navigationEvents.emit(
                ConfirmPasscodeNavigationEvent.NavigateToBiometricLock
            )
        }
    }

    private fun resetErrorState() {
        _state.update {
            it.copy(
                isPasscodeIncorrect = false,
                inputPasscode = ""
            )
        }
    }
}