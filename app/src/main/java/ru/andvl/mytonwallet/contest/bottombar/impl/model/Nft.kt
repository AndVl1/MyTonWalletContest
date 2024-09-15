package ru.andvl.mytonwallet.contest.bottombar.impl.model

data class Nft(
    val index: Int,
    val name: String? = null,
    val description: String? = null,
    val image: String,
    val address: String,
    val thumbnail: String
)
