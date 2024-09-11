package ru.andvl.mytonwallet.contest.blockchain.impl.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ApiLoyaltyType {
    @SerialName("black")
    BLACK,

    @SerialName("platinum")
    PLATINUM,

    @SerialName("gold")
    GOLD,

    @SerialName("silver")
    SILVER,

    @SerialName("standard")
    STANDARD
}