package ru.andvl.mytonwallet.contest.mappers

import ru.andvl.mytonwallet.contest.blockchain.impl.dtos.ApiStakingState
import ru.andvl.mytonwallet.contest.blockchain.impl.dtos.ApiUpdate
import ru.andvl.mytonwallet.contest.blockchain.impl.dtos.StakingStateResultDto
import ru.andvl.mytonwallet.contest.blockchain.impl.dtos.SwapTokenDto
import ru.andvl.mytonwallet.contest.blockchain.impl.dtos.TokenDto
import ru.andvl.mytonwallet.contest.database.entities.StakingStateEntity
import ru.andvl.mytonwallet.contest.database.entities.SwapTokenEntity
import ru.andvl.mytonwallet.contest.database.entities.TokenEntity

fun TokenDto.toEntity(): TokenEntity {
    return TokenEntity(
        slug = slug,
        name = name,
        symbol = symbol,
        decimals = decimals,
        minterAddress = minterAddress,
        image = image,
        isPopular = isPopular,
        cmcSlug = cmcSlug,
        color = color,
        price = quote.price,
        priceUsd = quote.priceUsd,
        percentChange24h = quote.percentChange24h
    )
}

fun SwapTokenDto.toEntity(): SwapTokenEntity {
    return SwapTokenEntity(
        slug = slug,
        name = name,
        symbol = symbol,
        blockchain = blockchain,
        decimals = decimals,
        image = image,
        isPopular = isPopular,
        color = color,
        price = price,
        priceUsd = priceUsd,
        contract = contract,
//        keywords = keywords
    )
}

fun StakingStateResultDto.toEntity(accountId: String): StakingStateEntity {
    return when (state) {
        is ApiStakingState.Nominators -> {
            val balance = state.amount.toBigInteger()

            StakingStateEntity(
                accountId = accountId,
                type = state.type,
                balance = balance.toString(),
                isUnstakeRequested = state.isUnstakeRequested,
                unstakeRequestedAmount = null,
                apy = backendState.nominatorsPool.apy,
                start = backendState.nominatorsPool.start,
                end = backendState.nominatorsPool.end,
                totalProfit = backendState.totalProfit
            )
        }

        is ApiStakingState.Liquid -> {
            val balance = state.amount.toBigInteger()

            StakingStateEntity(
                accountId = accountId,
                type = state.type,
                balance = balance.toString(),
                isUnstakeRequested = state.unstakeRequestAmount.isNotEmpty(),
                unstakeRequestedAmount = state.unstakeRequestAmount,
                start = 0L,
                end = 0L,
                apy = state.apy,
                totalProfit = backendState.totalProfit
            )
        }
    }
}

fun ApiUpdate.Stacking.toEntity(): StakingStateEntity {
    return when (stakingState) {
        is ApiStakingState.Nominators -> {
            val balance = stakingState.amount.toBigInteger()

            StakingStateEntity(
                accountId = accountId,
                type = stakingState.type,
                balance = balance.toString(),
                isUnstakeRequested = stakingState.isUnstakeRequested,
                unstakeRequestedAmount = null,
                apy = backendStakingState.nominatorsPool.apy,
                start = backendStakingState.nominatorsPool.start,
                end = backendStakingState.nominatorsPool.end,
                totalProfit = backendStakingState.totalProfit
            )
        }

        is ApiStakingState.Liquid -> {
            val isPrevRoundUnlocked =
                System.currentTimeMillis() > stakingCommonData.prevRound.unlock
            val balance = stakingState.amount.toBigInteger()

            StakingStateEntity(
                accountId = accountId,
                type = stakingState.type,
                balance = balance.toString(),
                isUnstakeRequested = stakingState.unstakeRequestAmount.isNotEmpty(),
                unstakeRequestedAmount = stakingState.unstakeRequestAmount,
                start = if (isPrevRoundUnlocked) stakingCommonData.round.start else stakingCommonData.prevRound.start,
                end = if (isPrevRoundUnlocked) stakingCommonData.round.unlock else stakingCommonData.prevRound.unlock,
                apy = stakingState.apy,
                totalProfit = backendStakingState.totalProfit
            )
        }
    }
}