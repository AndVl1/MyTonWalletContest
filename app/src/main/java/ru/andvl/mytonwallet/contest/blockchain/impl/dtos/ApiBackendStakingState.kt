package ru.andvl.mytonwallet.contest.blockchain.impl.dtos

import kotlinx.serialization.Serializable

@Serializable
data class ApiBackendStakingState(
    val balance: String,
    val totalProfit: String,
    val type: ApiStakingType? = null,
    val nominatorsPool: ApiNominatorsPool,
    val loyaltyType: ApiLoyaltyType? = null,
    val shouldUseNominators: Boolean? = null,
    val stakedAt: Long? = null
)