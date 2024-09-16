package ru.andvl.mytonwallet.contest.bottombar.impl.model

import androidx.annotation.DrawableRes
import ru.andvl.mytonwallet.contest.R

enum class BottomBarEnum(
    val tabName: String,
    @DrawableRes val iconId: Int
) {
    WALLET("Wallet", R.drawable.ic_wallet_bottom_bar),
    ASSETS("Assets", R.drawable.ic_assets_bottom_bar),
    BROWSER("Browser", R.drawable.ic_browser_bottom_bar),
    SETTINGS("Browser", R.drawable.ic_settings_bottom_bar)
}