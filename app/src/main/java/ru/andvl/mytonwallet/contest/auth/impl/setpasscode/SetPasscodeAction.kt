package ru.andvl.mytonwallet.contest.auth.impl.setpasscode

import ru.andvl.mytonwallet.contest.arch.Action

sealed interface SetPasscodeAction : Action {
    data class OnNumberKeyboardButtonClicked(
        val button: NumberKeyboardButtonItem
    ) : SetPasscodeAction

    data object TogglePasscodeLength : SetPasscodeAction
    data object NavigateBack : SetPasscodeAction
    data object NavigateNext : SetPasscodeAction
}