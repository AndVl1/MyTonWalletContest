package ru.andvl.mytonwallet.contest.utils

import ru.andvl.mytonwallet.contest.blockchain.util.TONCOIN_SYMBOL
import ru.andvl.mytonwallet.contest.bottombar.impl.model.Token
import java.math.BigDecimal

private val BTC = setOf("jWBTC", "oWBTC", "BTC")
private val USD = setOf("jUSDT", "oUSDT", "USDT", "jUSDC", "oUSDC", "USDC", "USD₮")
private val FIAT = setOf("USD", "RUB", "EUR", "CNY")

const val LARGE_NUMBER = 1000

fun getCurrencyPriority(symbol: String): Int {
    if (FIAT.contains(symbol)) return 5
    if (USD.contains(symbol)) return 4
    if (BTC.contains(symbol)) return 3
    if (symbol == TONCOIN_SYMBOL) return 2

    return 1
}

fun getSwapRate(
    fromAmount: BigDecimal,
    toAmount: BigDecimal,
    fromToken: Token,
    toToken: Token,
    shouldTrimLargeNumber: Boolean = false
): String {
    var firstCurrencySymbol = fromToken.symbol ?: TONCOIN_SYMBOL
    var secondCurrencySymbol = toToken.symbol ?: TONCOIN_SYMBOL
    val price: String

    val fromPriority = getCurrencyPriority(firstCurrencySymbol)
    val toPriority = getCurrencyPriority(secondCurrencySymbol)

    if (toPriority < fromPriority) {
        firstCurrencySymbol = toToken.symbol ?: TONCOIN_SYMBOL
        secondCurrencySymbol = fromToken.symbol ?: TONCOIN_SYMBOL
        val ratio = fromAmount.div(toAmount)
        val isLargeNumber = shouldTrimLargeNumber && ratio >= LARGE_NUMBER.toBigDecimal()
        price = formatInteger(
            value = ratio,
            fractionDigits = if (isLargeNumber) 0 else 4
        )
    } else {
        val ratio = toAmount.div(fromAmount)
        val isLargeNumber = shouldTrimLargeNumber && ratio >= LARGE_NUMBER.toBigDecimal()
        price = formatInteger(
            value = ratio,
            fractionDigits = if (isLargeNumber) 0 else 4
        )
    }

    return "$price $secondCurrencySymbol"
}