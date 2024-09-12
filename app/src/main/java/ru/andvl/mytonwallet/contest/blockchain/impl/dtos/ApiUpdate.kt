package ru.andvl.mytonwallet.contest.blockchain.impl.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class ApiUpdate {
    abstract val updateType: ApiUpdateType

    @Serializable
    @SerialName("updateBalances")
    data class Balances(
        override val updateType: ApiUpdateType = ApiUpdateType.BALANCES,
        val accountId: String,
        val balancesToUpdate: Map<String, String>
    ) : ApiUpdate()

    @Serializable
    @SerialName("updateTokens")
    data class Tokens(
        override val updateType: ApiUpdateType = ApiUpdateType.TOKENS,
        val tokens: Map<String, TokenDto>,
        val baseCurrency: String
    ) : ApiUpdate()

    @Serializable
    @SerialName("updateStaking")
    data class Stacking(
        override val updateType: ApiUpdateType = ApiUpdateType.STAKING,
        val accountId: String,
        val stakingCommonData: ApiStakingCommonData,
        val stakingState: ApiStakingState,
        val backendStakingState: ApiBackendStakingState
    ) : ApiUpdate()

    @Serializable
    @SerialName("newActivities")
    data class NewActivities(
        override val updateType: ApiUpdateType = ApiUpdateType.NEW_ACTIVITIES,
        val accountId: String,
        val activities: List<ApiActivity>,
        val noForward: Boolean? = null,
    ) : ApiUpdate()
}

@Serializable
enum class ApiUpdateType {
    @SerialName("updateBalances")
    BALANCES,

    @SerialName("updateTokens")
    TOKENS,

    @SerialName("updateStaking")
    STAKING,

    @SerialName("newActivities")
    NEW_ACTIVITIES,
}

@Serializable
data class ApiNewActivities(
    val accountId: String,
    val activities: List<ApiActivity>,
    val noForward: Boolean? = null,
)