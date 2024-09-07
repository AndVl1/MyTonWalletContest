package ru.andvl.mytonwallet.contest.blockchain.impl.dtos

import kotlinx.serialization.Serializable

@Serializable
data class TokenDto(
    val name: String,
    val symbol: String,
    val slug: String,
    val decimals: Int,
    val minterAddress: String? = null,
    val image: String? = null,
    val isPopular: Boolean? = null,
    val keywords: List<String>? = null,
    val cmcSlug: String? = null,
    val color: String? = null,
    val quote: TokenQuoteDto
)