package ru.andvl.mytonwallet.contest.auth.impl.passcode

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.arch.State
import ru.andvl.mytonwallet.contest.auth.impl.model.KeyboardDigitWithDescription

data class PasscodeState(
    val correctPasscodeLength: Int = PasscodeLength.FOUR.value,
    val inputPasscode: String = "",
    val isPasswordIncorrect: Boolean = false
) : State {
    val keyboardButtons: ImmutableList<ImmutableList<PasscodeButtonItem>> = persistentListOf(
        persistentListOf(
            PasscodeButtonItem.DigitButton(KeyboardDigitWithDescription.ONE),
            PasscodeButtonItem.DigitButton(KeyboardDigitWithDescription.TWO),
            PasscodeButtonItem.DigitButton(KeyboardDigitWithDescription.THREE)
        ),
        persistentListOf(
            PasscodeButtonItem.DigitButton(KeyboardDigitWithDescription.FOUR),
            PasscodeButtonItem.DigitButton(KeyboardDigitWithDescription.FIVE),
            PasscodeButtonItem.DigitButton(KeyboardDigitWithDescription.SIX)
        ),
        persistentListOf(
            PasscodeButtonItem.DigitButton(KeyboardDigitWithDescription.SEVEN),
            PasscodeButtonItem.DigitButton(KeyboardDigitWithDescription.EIGHT),
            PasscodeButtonItem.DigitButton(KeyboardDigitWithDescription.NINE)
        ),
        persistentListOf(
            PasscodeButtonItem.ActionButton(
                type = KeyboardActionType.FINGERPRINT,
                icon = R.drawable.ic_fingerprint,
            ),
            PasscodeButtonItem.DigitButton(KeyboardDigitWithDescription.ZERO),
            PasscodeButtonItem.ActionButton(
                type = KeyboardActionType.DELETE,
                icon = R.drawable.ic_del
            )
        )
    )
}