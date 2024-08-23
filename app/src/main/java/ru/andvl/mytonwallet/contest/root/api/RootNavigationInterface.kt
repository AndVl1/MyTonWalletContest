package ru.andvl.mytonwallet.contest.root.api

import androidx.compose.runtime.staticCompositionLocalOf
import ru.andvl.mytonwallet.contest.root.api.model.RootScreenConfig

val LocalRootNavigation = staticCompositionLocalOf<RootNavigationInterface> {
    error("CompositionLocal LocalRootComponent not present")
}

interface RootNavigationInterface {
    fun push(config: RootScreenConfig)
}
