package ru.andvl.mytonwallet.contest.blockchain.impl.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ApiSwapCexTransactionStatus {
    @SerialName("new")
    NEW,

    @SerialName("waiting")
    WAITING,

    @SerialName("confirming")
    CONFIRMING,

    @SerialName("exchanging")
    EXCHANGING,

    @SerialName("sending")
    SENDING,

    @SerialName("finished")
    FINISHED,

    @SerialName("failed")
    FAILED,

    @SerialName("refunded")
    REFUNDED,

    @SerialName("hold")
    HOLD,

    @SerialName("overdue")
    OVERDUE,

    @SerialName("expired")
    EXPIRED
}