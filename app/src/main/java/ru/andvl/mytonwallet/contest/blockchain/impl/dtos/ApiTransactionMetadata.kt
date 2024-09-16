package ru.andvl.mytonwallet.contest.blockchain.impl.dtos

import kotlinx.serialization.Serializable

@Serializable
data class ApiTransactionMetadata(
    override val name: String? = null,
    override val isScam: Boolean? = null,
    override val isMemoRequired: Boolean? = null
) : ApiAddressInfo