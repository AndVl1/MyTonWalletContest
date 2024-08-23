package ru.andvl.mytonwallet.contest.auth.impl.passcode

import ru.andvl.mytonwallet.contest.arch.Action

sealed interface PasscodeAction : Action {
    data class OnPasscodeButtonClicked(val button: PasscodeButtonItem) : PasscodeAction
    data object Fingerprint : PasscodeAction
    data object CheckPasscode : PasscodeAction
    data object ResetErrorState : PasscodeAction
    data object NavigateNext : PasscodeAction
}