package ru.andvl.mytonwallet.contest.auth.impl.setpasscode

import androidx.annotation.DrawableRes
import ru.andvl.mytonwallet.contest.auth.impl.model.KeyboardDigitWithDescription

sealed class NumberKeyboardButtonItem {
    data class DigitButton(
        val digitWithDescription: KeyboardDigitWithDescription
    ) : NumberKeyboardButtonItem()

    data class ActionButton(
        val type: NumberKeyboardActionType,
        @DrawableRes val icon: Int
    ) : NumberKeyboardButtonItem()
}