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
    private val _state = MutableStateFlow(SetPasscodeState())

    override val state = _state.asStateFlow()

    private val _navigationEvents = MutableSharedFlow<SetPasscodeNavigationEvent>()
    val navigationEvents = _navigationEvents.asSharedFlow()

    override fun obtainEvent(event: SetPasscodeAction) {
        viewModelScope.launch {
            when (event) {
                is SetPasscodeAction.TogglePasscodeLength -> togglePasscodeLength()

                is SetPasscodeAction.OnNumberKeyboardButtonClicked -> onNumberKeyboardButtonClicked(
                    button = event.button
                )

                is SetPasscodeAction.NavigateToConfirm -> {
                    _state.value.let {
                        _navigationEvents.emit(
                            SetPasscodeNavigationEvent.NavigateToConfirm(
                                passcode = it.inputPasscode,
                                passcodeLength = it.passcodeLength
                            )
                        )
                    }
                    _state.update { SetPasscodeState() }
                }

                is SetPasscodeAction.NavigateBack -> {
                    _navigationEvents.emit(SetPasscodeNavigationEvent.NavigateBack)
                    _state.update { SetPasscodeState() }
                }
            }
        }
    }

    private fun togglePasscodeLength() {
        val newLength = if (_state.value.passcodeLength == PasscodeLength.FOUR)
            PasscodeLength.SIX else PasscodeLength.FOUR

        _state.update {
            it.copy(
                passcodeLength = newLength,
                inputPasscode = ""
            )
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
                    obtainEvent(SetPasscodeAction.NavigateToConfirm)
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
}