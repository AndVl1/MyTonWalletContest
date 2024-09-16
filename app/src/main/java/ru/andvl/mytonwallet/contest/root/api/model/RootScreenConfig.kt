package ru.andvl.mytonwallet.contest.root.api.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface RootScreenConfig {
    @Serializable
    object Loading : RootScreenConfig

    @Serializable
    object AddWallet : RootScreenConfig
    @Serializable
    object Login : RootScreenConfig
    @Serializable
    object Main : RootScreenConfig
}
