package ru.andvl.mytonwallet.contest.blockchain.impl.dtos

import kotlinx.serialization.Serializable

@Serializable
data class CexTransaction(
    val payinAddress: String,
    val payoutAddress: String,
    val payinExtraId: String? = null,
    val status: ApiSwapCexTransactionStatus,
    val transactionId: String
)