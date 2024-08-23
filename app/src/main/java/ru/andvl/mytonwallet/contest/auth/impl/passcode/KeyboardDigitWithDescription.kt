package ru.andvl.mytonwallet.contest.auth.impl.passcode

enum class KeyboardDigitWithDescription(val digit: Int, val description: String) {
    ONE(1, ""),
    TWO(2, "ABC"),
    THREE(3, "DEF"),
    FOUR(4, "GHI"),
    FIVE(5, "JKL"),
    SIX(6, "MNO"),
    SEVEN(7, "PQRS"),
    EIGHT(8, "TUV"),
    NINE(9, "WXYZ"),
    ZERO(0, "")
}