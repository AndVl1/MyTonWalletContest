package ru.andvl.mytonwallet.contest.utils

import java.math.BigDecimal
import java.math.RoundingMode

const val DEFAULT_DECIMAL_PLACES = 9
const val WHOLE_PART_DELIMITER = ' '
const val USD_SYMBOL = "$"

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

fun formatTokenPrice(value: Float, currency: String = USD_SYMBOL): String {
    val formatted = "%.2f".format(value).replace(',', '.')
    return "$currency$formatted"
}

fun formatPercent(value: Float): String {
    return "%.2f".format(value).replace(',', '.').trimEnd('0').trimEnd(',', '.')
}

fun formatCurrency(value: BigDecimal, currency: String = USD_SYMBOL): String {
    val formatted = formatInteger(
        value = value,
        noFloor = true
    )
    return "$currency $formatted"
}

fun formatTokenCurrency(value: BigDecimal, symbol: String): String {
    val formatted = formatInteger(value = value).let {
        if (value / BigDecimal(10000) != BigDecimal.ZERO) it
        else it.replace(" ", "")
    }
    return "$formatted $symbol"
}

fun formatTokenAmountUsd(value: BigDecimal): String {
    val formatted = formatInteger(
        value = value,
        wholePartDelimiter = ','
    )
    return "$USD_SYMBOL$formatted"
}

fun formatInteger(
    value: BigDecimal,
    fractionDigits: Int = 2,
    noRadix: Boolean = false,
    noFloor: Boolean = false,
    decimalPlaces: Int = DEFAULT_DECIMAL_PLACES,
    wholePartDelimiter: Char = WHOLE_PART_DELIMITER
): String {
    val dp = if (value >= BigDecimal.ONE || noFloor) fractionDigits else decimalPlaces

    var fixed = value.setScale(dp, if (noFloor) RoundingMode.HALF_UP else RoundingMode.DOWN)
        .toPlainString()

    if (value < BigDecimal.ONE && countSignificantDigits(fixed) < fractionDigits) {
        fixed = value.toPlainString()
    }

    var (wholePart, fractionPart) = fixed.split('.', limit = 2).let {
        it[0] to (it.getOrNull(1) ?: "")
    }

    fractionPart = toSignificant(fractionPart, fractionDigits).replace(Regex("0+$"), "")

    if (fractionPart.isEmpty()) {
        wholePart = wholePart.replace(Regex("^-0$"), "0")
    }

    if (!noRadix) {
        wholePart = wholePart.replace(Regex("(\\d)(?=(\\d{3})+(\\.|$))"), "$1$wholePartDelimiter")
    }

    return listOf(wholePart, fractionPart).filter { it.isNotEmpty() }.joinToString(".")
}

fun countSignificantDigits(value: String): Int {
    val decimalIndex = value.indexOf('.')
    if (decimalIndex == -1) {
        return 0
    }

    val fractionalPart = value.substring(decimalIndex + 1).replace(Regex("^0+"), "")
    return fractionalPart.length
}

fun toSignificant(value: String, fractionDigits: Int): String {
    var digitsCount = 0
    var digitsLastIndex = 0

    for (i in value.indices) {
        digitsLastIndex += 1

        if (value[i] == '0' && digitsCount == 0) {
            continue
        }

        digitsCount += 1

        if (digitsCount == fractionDigits) {
            break
        }
    }

    return value.substring(0, digitsLastIndex).replace(Regex("0+$"), "")
}