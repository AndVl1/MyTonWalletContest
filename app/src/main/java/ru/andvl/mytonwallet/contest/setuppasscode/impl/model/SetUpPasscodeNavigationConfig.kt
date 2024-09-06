package ru.andvl.mytonwallet.contest.setuppasscode.impl.model

import kotlinx.serialization.Serializable
import ru.andvl.mytonwallet.contest.auth.impl.passcode.PasscodeLength

@Serializable
sealed interface SetUpPasscodeNavigationConfig {
    @Serializable
    data object SetPasscodeScreen : SetUpPasscodeNavigationConfig

    @Serializable
    data class ConfirmPasscodeScreen(
        val correctPasscode: String,
        val passcodeLength: PasscodeLength
    ) : SetUpPasscodeNavigationConfig

    @Serializable
    data class BiometricLockScreen(
        val passcode: String
    ) : SetUpPasscodeNavigationConfig
}