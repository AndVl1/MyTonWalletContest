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
    class SetPasscodeScreen : WalletCreatedFlow

    @Serializable
    class BiometricLockScreen : WalletCreatedFlow

    @Serializable
    class RecoveryListScreen : WalletCreatedFlow

    @Serializable
    class RecoveryTestScreen : WalletCreatedFlow

    sealed interface WalletImportFlow : AuthNavigationConfig

    @Serializable
    class WalletImportScreen : WalletImportFlow
}