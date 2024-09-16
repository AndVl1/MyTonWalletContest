package ru.andvl.mytonwallet.contest.blockchain.impl.dtos

import kotlinx.serialization.Serializable

@Serializable
data class StakingStateResultDto(
    val backendState: ApiBackendStakingState,
    val state: ApiStakingState
)
