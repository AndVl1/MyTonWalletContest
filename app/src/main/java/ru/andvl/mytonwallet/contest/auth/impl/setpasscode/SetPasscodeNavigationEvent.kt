package ru.andvl.mytonwallet.contest.auth.impl.setpasscode

import ru.andvl.mytonwallet.contest.auth.impl.passcode.PasscodeLength

sealed interface SetPasscodeNavigationEvent {
    data object NavigateBack : SetPasscodeNavigationEvent
    data class NavigateToConfirm(
        val passcode: String,
        val passcodeLength: PasscodeLength
    ) : SetPasscodeNavigationEvent
}