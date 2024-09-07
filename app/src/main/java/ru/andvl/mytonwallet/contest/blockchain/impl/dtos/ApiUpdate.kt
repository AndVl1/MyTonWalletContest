package ru.andvl.mytonwallet.contest.blockchain.impl.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class ApiUpdate {
    abstract val type: String

    @Serializable
    @SerialName("updateBalances")
    data class Balances(
        override val type: String = "updateBalances",
        val accountId: String,
        val balancesToUpdate: Map<String, String>
    ) : ApiUpdate()

    @Serializable
    @SerialName("updateTokens")
    data class Tokens(
        override val type: String = "updateTokens",
        val tokens: Map<String, TokenDto>,
        val baseCurrency: String
    ) : ApiUpdate()
}