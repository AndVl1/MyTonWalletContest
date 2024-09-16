package ru.andvl.mytonwallet.contest.bottombar.impl.utils

const val DEFAULT_SHIFT = 6
const val FILLER = "..."
const val FILLER_LENGTH = FILLER.length

fun getShortenAddress(
    address: String,
    shift: Int = DEFAULT_SHIFT,
    fromRight: Int = DEFAULT_SHIFT
): String {
    return if (address.length <= shift + fromRight + FILLER_LENGTH) address
    else address.slice(0 until shift) +
            FILLER +
            address.slice(address.length - fromRight until address.length)
}