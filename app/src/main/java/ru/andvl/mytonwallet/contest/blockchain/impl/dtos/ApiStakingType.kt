package ru.andvl.mytonwallet.contest.blockchain.impl.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ApiStakingType {
    @SerialName("nominators")
    NOMINATORS,

    @SerialName("liquid")
    LIQUID
}