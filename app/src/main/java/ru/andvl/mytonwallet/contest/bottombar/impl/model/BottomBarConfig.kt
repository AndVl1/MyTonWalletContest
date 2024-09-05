package ru.andvl.mytonwallet.contest.bottombar.impl.model

import kotlinx.serialization.Serializable

@Serializable
sealed class BottomBarConfig {
    abstract val enum: BottomBarEnum

    @Serializable
    data object WalletScreen : BottomBarConfig() {
        override val enum = BottomBarEnum.WALLET
    }

    @Serializable
    data object AssetsScreen : BottomBarConfig() {
        override val enum = BottomBarEnum.ASSETS
    }

    @Serializable
    data object BrowserScreen : BottomBarConfig() {
        override val enum = BottomBarEnum.BROWSER
    }

    @Serializable
    data object SettingsScreen : BottomBarConfig() {
        override val enum = BottomBarEnum.SETTINGS
    }
}