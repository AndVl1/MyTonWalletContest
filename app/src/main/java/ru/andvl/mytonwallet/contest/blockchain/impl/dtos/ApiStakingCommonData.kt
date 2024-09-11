package ru.andvl.mytonwallet.contest.blockchain.impl.dtos

import kotlinx.serialization.Serializable

@Serializable
data class ApiStakingCommonData(
    val liquid: LiquidData,
    val round: RoundData,
    val prevRound: RoundData,
    val bigInt: String
)

@Serializable
data class LiquidData(
    val currentRate: Double,
    val nextRoundRate: Double,
    val collection: String? = null,
    val apy: Double,
    val available: String,
    val loyaltyApy: Map<ApiLoyaltyType, Double>
)

@Serializable
data class RoundData(
    val start: Long,
    val end: Long,
    val unlock: Long
)