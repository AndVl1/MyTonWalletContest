package ru.andvl.mytonwallet.contest.decompose

import androidx.compose.runtime.Composable

abstract class DecomposeComponent internal constructor() {
    @Composable
    abstract fun Render()
}
