package ru.andvl.mytonwallet.contest.decompose

import com.arkivanov.decompose.ComponentContext

abstract class ScreenDecomposeComponent(
    componentContext: ComponentContext
) : DecomposeComponent(), ComponentContext by componentContext
