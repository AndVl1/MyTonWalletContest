package ru.andvl.mytonwallet.contest.blockchain.impl.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class SwapStatus {
    @SerialName("pending")
    PENDING,

    @SerialName("completed")
    COMPLETED,

    @SerialName("failed")
    FAILED,

    @SerialName("expired")
    EXPIRED
}