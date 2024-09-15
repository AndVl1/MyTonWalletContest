package ru.andvl.mytonwallet.contest.blockchain.impl.dtos

import kotlinx.serialization.Serializable

@Serializable
data class SwapTokenDto(
    val name: String,
    val symbol: String,
    val blockchain: String,
    val slug: String,
    val decimals: Int,
    val isPopular: Boolean,
    val price: Float,
    val priceUsd: Float,
    val image: String?,
    val contract: String?,
    val keywords: List<String>?,
    val color: String?
)
