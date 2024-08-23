package ru.andvl.mytonwallet.contest.auth.impl.passcode

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.andvl.mytonwallet.contest.arch.BaseViewModel

class PasscodeViewModel : BaseViewModel<PasscodeAction, PasscodeState>() {
    private val _state = MutableStateFlow(PasscodeState())

    override val state = _state.asStateFlow()

    private val _navigationEvents = MutableSharedFlow<PasscodeNavigationEvent>()
    val navigationEvents = _navigationEvents.asSharedFlow()

    override fun obtainEvent(event: PasscodeAction) {
        viewModelScope.launch {
            when (event) {
                is PasscodeAction.NavigateNext -> {
                    _navigationEvents.emit(PasscodeNavigationEvent.NavigateToWallet)
                }

                is PasscodeAction.OnPasscodeButtonClicked -> {
                    event.button.let { button ->
                        when (button) {
                            is PasscodeButtonItem.DigitButton -> {
                                updateInputPasscode(button.digitWithDescription.digit)
                            }

                            is PasscodeButtonItem.ActionButton -> {
                                when (button.type) {
                                    KeyboardActionType.FINGERPRINT -> {
                                        obtainEvent(PasscodeAction.Fingerprint)
                                    }

                                    KeyboardActionType.DELETE -> {
                                        _state.update {
                                            it.copy(inputPasscode = it.inputPasscode.dropLast(1))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                is PasscodeAction.Fingerprint -> { /*TODO*/
                }

                is PasscodeAction.CheckPasscode -> onPasscodeCheck()

                is PasscodeAction.ResetErrorState -> resetErrorState()
            }
        }
    }

    private fun updateInputPasscode(digit: Int) {
        if (_state.value.inputPasscode.length < _state.value.correctPasscodeLength) {
            _state.update {
                it.copy(
                    inputPasscode = it.inputPasscode + digit
                )
            }

            _state.value.let {
                if (it.inputPasscode.length == it.correctPasscodeLength) {
                    obtainEvent(PasscodeAction.CheckPasscode)
                }
            }
        }
    }

    private fun onPasscodeCheck() {
        val isCorrect = checkPasscode(_state.value.inputPasscode)
        _state.update { currentState ->
            currentState.copy(isPasswordIncorrect = !isCorrect)
        }

        if (isCorrect) obtainEvent(PasscodeAction.NavigateNext)
    }

    private fun checkPasscode(input: String): Boolean {
        return input == "1234"
    }

    private fun resetErrorState() {
        _state.update {
            it.copy(
                isPasswordIncorrect = false,
                inputPasscode = ""
            )
        }
    }
}