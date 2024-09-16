package ru.andvl.mytonwallet.contest.blockchain.impl.dtos

interface ApiAddressInfo {
    val name: String?
    val isScam: Boolean?
    val isMemoRequired: Boolean?
}