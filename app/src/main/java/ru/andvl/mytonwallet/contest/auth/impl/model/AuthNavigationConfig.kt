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
    data class RecoveryListScreen(
        val passcode: String
    ) : WalletCreatedFlow

    @Serializable
    data class RecoveryTestScreen(
        val recoveryWords: List<String>,
        val passcode: String
    ) : WalletCreatedFlow

    sealed interface WalletImportFlow : AuthNavigationConfig

    @Serializable
    class WalletImportScreen : WalletImportFlow

    @Serializable
    data class WalletImportSetUpPasscode(
        val mnemonic: List<String>
    ) : WalletImportFlow
}