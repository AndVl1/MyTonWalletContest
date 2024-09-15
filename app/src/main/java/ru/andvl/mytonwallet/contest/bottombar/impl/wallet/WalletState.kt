package ru.andvl.mytonwallet.contest.bottombar.impl.wallet

import ru.andvl.mytonwallet.contest.arch.State
import ru.andvl.mytonwallet.contest.bottombar.impl.model.AssetToken
import ru.andvl.mytonwallet.contest.bottombar.impl.model.HistoryActivity
import java.math.BigInteger

data class WalletState(
    val balance: BigInteger = BigInteger.ZERO,
    val assetTokens: List<AssetToken> = emptyList(),
    val historyActivities: List<HistoryActivity> = emptyList()
) : State