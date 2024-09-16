package ru.andvl.mytonwallet.contest.blockchain.impl.dtos

import kotlinx.serialization.Serializable

@Serializable
data class AuthResultDto(
    val accountId: String? = null,
    val address: String? = null,
    val error: String? = null
)
