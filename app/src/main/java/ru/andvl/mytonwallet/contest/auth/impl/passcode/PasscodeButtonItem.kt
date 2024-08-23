package ru.andvl.mytonwallet.contest.auth.impl.passcode

import androidx.annotation.DrawableRes

sealed class PasscodeButtonItem {
    data class DigitButton(
        val digitWithDescription: KeyboardDigitWithDescription
    ) : PasscodeButtonItem()

    data class ActionButton(
        val type: KeyboardActionType,
        @DrawableRes val icon: Int
    ) : PasscodeButtonItem()
}