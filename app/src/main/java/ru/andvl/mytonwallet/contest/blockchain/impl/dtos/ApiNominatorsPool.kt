package ru.andvl.mytonwallet.contest.blockchain.impl.dtos

import kotlinx.serialization.Serializable

@Serializable
data class ApiNominatorsPool(
    val address: String,
    val apy: Float,
    val start: Long,
    val end: Long
)