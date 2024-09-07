package ru.andvl.mytonwallet.contest.blockchain.impl.dtos

import kotlinx.serialization.Serializable

@Serializable
data class TokenQuoteDto(
    val slug: String,
    val price: Double,
    val priceUsd: Double,
    val percentChange24h: Double
)