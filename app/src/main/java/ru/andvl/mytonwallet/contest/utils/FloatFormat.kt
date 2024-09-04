package ru.andvl.mytonwallet.contest.utils

fun formatBalanceOrTransactionAmount(value: Float): String {
    val intPart = value.toInt()
    val fractionalPart = value % 1

    val intPartFormatted = intPart.toString()
        .reversed()
        .chunked(3)
        .joinToString(" ")
        .reversed()

    return if (fractionalPart == 0f) {
        intPartFormatted
    } else {
        val fractionalPartFormatted = "%.2f".format(fractionalPart).substring(1).trimEnd('0')

        "$intPartFormatted$fractionalPartFormatted"
    }
}