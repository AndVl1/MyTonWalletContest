package ru.andvl.mytonwallet.contest.bottombar.impl.wallet

import ru.andvl.mytonwallet.contest.arch.State
import ru.andvl.mytonwallet.contest.bottombar.impl.model.AssetToken

data class WalletState(
    val assetTokens: List<AssetToken> = emptyList()
) : State