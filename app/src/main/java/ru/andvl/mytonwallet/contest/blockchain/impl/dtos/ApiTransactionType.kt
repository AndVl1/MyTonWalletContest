package ru.andvl.mytonwallet.contest.blockchain.impl.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ApiTransactionType {
    @SerialName("stake")
    STAKE,

    @SerialName("unstake")
    UNSTAKE,

    @SerialName("unstakeRequest")
    UNSTAKE_REQUEST,

    @SerialName("swap")
    SWAP,

    @SerialName("nftTransferred")
    NFT_TRANSFERRED,

    @SerialName("nftReceived")
    NFT_RECEIVED
}