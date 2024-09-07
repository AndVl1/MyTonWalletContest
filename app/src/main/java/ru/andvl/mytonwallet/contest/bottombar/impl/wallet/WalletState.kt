package ru.andvl.mytonwallet.contest.bottombar.impl.wallet

import ru.andvl.mytonwallet.contest.arch.State
import ru.andvl.mytonwallet.contest.bottombar.impl.model.AssetToken
import java.math.BigInteger

data class WalletState(
    val balance: BigInteger = BigInteger.ZERO,
    val assetTokens: List<AssetToken> = emptyList()
) : State