package ru.andvl.mytonwallet.contest.bottombar.impl.model

data class Token(
    val slug: String,
    val name: String,
    val image: TokenImage?,
    val price: Float,
    val symbol: String? = null,
)
