package ru.andvl.mytonwallet.contest.auth.impl.confirmpasscode

import ru.andvl.mytonwallet.contest.arch.Action
import ru.andvl.mytonwallet.contest.auth.impl.setpasscode.NumberKeyboardButtonItem

sealed interface ConfirmPasscodeAction : Action {
    data class OnNumberKeyboardButtonClicked(
        val button: NumberKeyboardButtonItem
    ) : ConfirmPasscodeAction

    data object NavigateBack : ConfirmPasscodeAction
    data object Confirm : ConfirmPasscodeAction
    data object ResetErrorState : ConfirmPasscodeAction
}