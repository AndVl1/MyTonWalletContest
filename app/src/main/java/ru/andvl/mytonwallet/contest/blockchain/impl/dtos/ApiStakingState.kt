package ru.andvl.mytonwallet.contest.blockchain.impl.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
sealed interface ApiStakingState {
    @Serializable
    @SerialName("nominators")
    data class Nominators(
        val type: ApiStakingType = ApiStakingType.NOMINATORS,
        val amount: String,
        val pendingDepositAmount: String,
        val isUnstakeRequested: Boolean
    ) : ApiStakingState

    @Serializable
    @SerialName("liquid")
    data class Liquid(
        val type: ApiStakingType = ApiStakingType.LIQUID,
        val tokenAmount: String,
        val amount: String,
        val unstakeRequestAmount: String,
        val apy: Float,
        val instantAvailable: String
    ) : ApiStakingState
}