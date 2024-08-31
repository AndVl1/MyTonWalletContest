package ru.andvl.mytonwallet.contest.auth.impl.model

import kotlinx.serialization.Serializable
import ru.andvl.mytonwallet.contest.auth.impl.passcode.PasscodeLength

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
    data class ConfirmPasscodeScreen(
        val correctPasscode: String,
        val passcodeLength: PasscodeLength
    ) : WalletCreatedFlow

    @Serializable
    class BiometricLockScreen : WalletCreatedFlow

    @Serializable
    class RecoveryListScreen : WalletCreatedFlow

    @Serializable
    data class RecoveryTestScreen(
        val recoveryWords: List<String>
    ) : WalletCreatedFlow

    sealed interface WalletImportFlow : AuthNavigationConfig

    @Serializable
    class WalletImportScreen : WalletImportFlow
}