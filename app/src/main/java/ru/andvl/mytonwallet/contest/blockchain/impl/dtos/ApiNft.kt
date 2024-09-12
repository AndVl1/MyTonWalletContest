package ru.andvl.mytonwallet.contest.blockchain.impl.dtos

import kotlinx.serialization.Serializable

@Serializable
data class ApiNft(
    val index: Int,
    val name: String? = null,
    val address: String,
    val thumbnail: String,
    val image: String,
    val description: String? = null,
    val collectionName: String? = null,
    val collectionAddress: String? = null,
    val isOnSale: Boolean,
    val isHidden: Boolean? = null,
    val isOnFragment: Boolean? = null,
    val isScam: Boolean? = null
)