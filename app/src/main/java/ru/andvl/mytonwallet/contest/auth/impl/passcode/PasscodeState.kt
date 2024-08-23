package ru.andvl.mytonwallet.contest.auth.impl.passcode

import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.arch.State

data class PasscodeState(
    val keyboardButtons: List<PasscodeButtonItem> = listOf(
        PasscodeButtonItem.DigitButton(KeyboardDigitWithDescription.ONE),
        PasscodeButtonItem.DigitButton(KeyboardDigitWithDescription.TWO),
        PasscodeButtonItem.DigitButton(KeyboardDigitWithDescription.THREE),
        PasscodeButtonItem.DigitButton(KeyboardDigitWithDescription.FOUR),
        PasscodeButtonItem.DigitButton(KeyboardDigitWithDescription.FIVE),
        PasscodeButtonItem.DigitButton(KeyboardDigitWithDescription.SIX),
        PasscodeButtonItem.DigitButton(KeyboardDigitWithDescription.SEVEN),
        PasscodeButtonItem.DigitButton(KeyboardDigitWithDescription.EIGHT),
        PasscodeButtonItem.DigitButton(KeyboardDigitWithDescription.NINE),
        PasscodeButtonItem.ActionButton(
            type = KeyboardActionType.FINGERPRINT,
            icon = R.drawable.ic_fingerprint,
        ),
        PasscodeButtonItem.DigitButton(KeyboardDigitWithDescription.ZERO),
        PasscodeButtonItem.ActionButton(
            type = KeyboardActionType.DELETE,
            icon = R.drawable.ic_del,
        )
    ),

    val correctPasscodeLength: Int = PasscodeLength.FOUR.value,
    val inputPasscode: String = "",
    val isPasswordIncorrect: Boolean = false
) : State