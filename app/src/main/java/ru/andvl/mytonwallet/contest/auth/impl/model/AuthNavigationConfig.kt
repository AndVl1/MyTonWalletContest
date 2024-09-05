package ru.andvl.mytonwallet.contest.auth.impl.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface AuthNavigationConfig {
    sealed interface StartDestination : AuthNavigationConfig

    @Serializable
    object NoWalletScreen : StartDestination

    @Serializable
    object PasscodeScreen : StartDestination

    sealed interface WalletCreatedFlow : AuthNavigationConfig
    @Serializable
    class WalletCreatedStartScreen : WalletCreatedFlow

    @Serializable
    data object WalletCreatedSetUpPasscode : WalletCreatedFlow

    @Serializable
    class RecoveryListScreen : WalletCreatedFlow

    @Serializable
    data class RecoveryTestScreen(
        val recoveryWords: List<String>
    ) : WalletCreatedFlow

    sealed interface WalletImportFlow : AuthNavigationConfig

    @Serializable
    class WalletImportScreen : WalletImportFlow

    @Serializable
    data object WalletImportSetUpPasscode : WalletImportFlow
}