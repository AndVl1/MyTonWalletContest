package ru.andvl.mytonwallet.contest.blockchain.impl.dtos

data class ApiSwapHistoryItem(
    val id: String,
    val timestamp: Long,
    val lt: Long? = null,
    val from: String,
    val fromAmount: String,
    val to: String,
    val toAmount: String,
    val networkFee: Double,
    val swapFee: String,
    val status: SwapStatus,
    val txIds: List<String>,
    val cex: CexTransaction? = null
)