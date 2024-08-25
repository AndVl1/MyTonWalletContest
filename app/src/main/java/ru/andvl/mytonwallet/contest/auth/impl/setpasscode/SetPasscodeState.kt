package ru.andvl.mytonwallet.contest.auth.impl.setpasscode

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.arch.State
import ru.andvl.mytonwallet.contest.auth.impl.model.KeyboardDigitWithDescription
import ru.andvl.mytonwallet.contest.auth.impl.passcode.PasscodeLength

data class SetPasscodeState(
    val passcodeLength: PasscodeLength = PasscodeLength.FOUR,
    val inputPasscode: String = ""
) : State {
    val keyboardButtons: ImmutableList<ImmutableList<NumberKeyboardButtonItem>>
        get() = persistentListOf(
            persistentListOf(
                NumberKeyboardButtonItem.DigitButton(KeyboardDigitWithDescription.ONE),
                NumberKeyboardButtonItem.DigitButton(KeyboardDigitWithDescription.TWO),
                NumberKeyboardButtonItem.DigitButton(KeyboardDigitWithDescription.THREE)
            ),
            persistentListOf(
                NumberKeyboardButtonItem.DigitButton(KeyboardDigitWithDescription.FOUR),
                NumberKeyboardButtonItem.DigitButton(KeyboardDigitWithDescription.FIVE),
                NumberKeyboardButtonItem.DigitButton(KeyboardDigitWithDescription.SIX)
            ),
            persistentListOf(
                NumberKeyboardButtonItem.DigitButton(KeyboardDigitWithDescription.SEVEN),
                NumberKeyboardButtonItem.DigitButton(KeyboardDigitWithDescription.EIGHT),
                NumberKeyboardButtonItem.DigitButton(KeyboardDigitWithDescription.NINE)
            ),
            persistentListOf(
                NumberKeyboardButtonItem.DigitButton(KeyboardDigitWithDescription.ZERO_WITH_PLUS),
                NumberKeyboardButtonItem.ActionButton(
                    type = NumberKeyboardActionType.DELETE,
                    icon = R.drawable.ic_outline_del
                )
            )
        )
}