package ru.andvl.mytonwallet.contest.auth.impl.setpasscode

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.arch.State
import ru.andvl.mytonwallet.contest.auth.impl.model.KeyboardDigitWithDescription
import ru.andvl.mytonwallet.contest.auth.impl.passcode.PasscodeLength

sealed interface SetPasscodeState : State {
    val passcodeLength: PasscodeLength
    val inputPasscode: String

    data class SetUp(
        override val passcodeLength: PasscodeLength = PasscodeLength.FOUR,
        override val inputPasscode: String = ""
    ) : SetPasscodeState

    data class Confirm(
        val correctPasscode: String,
        override val passcodeLength: PasscodeLength,
        override val inputPasscode: String = "",
        val isPasscodeIncorrect: Boolean = false,
    ) : SetPasscodeState

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
                NumberKeyboardButtonItem.DigitButton(KeyboardDigitWithDescription.ZERO),
                NumberKeyboardButtonItem.ActionButton(
                    type = NumberKeyboardActionType.DELETE,
                    icon = R.drawable.ic_outline_del
                )
            )
        )
}