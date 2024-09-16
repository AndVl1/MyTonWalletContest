package ru.andvl.mytonwallet.contest.utils

import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.andvl.mytonwallet.contest.R

fun launchBiometricAuth(
    enrollLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>
) {
    if (Build.VERSION.SDK_INT >= 30) {
        val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
            putExtra(
                Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                BIOMETRIC_STRONG or DEVICE_CREDENTIAL
            )
        }
        enrollLauncher.launch(enrollIntent)
    }
}

class BiometricPromptManager(
    private val activity: AppCompatActivity
) {
    private val resultChannel = Channel<BiometricResult>()
    val promptResults = resultChannel.receiveAsFlow()

    @OptIn(DelicateCoroutinesApi::class)
    fun showBiometricPrompt(
        title: String = activity.getString(R.string.auth_passcode_fingerprint_title),
        description: String = activity.getString(R.string.auth_passcode_fingerprint_description)
    ) {
        val manager = BiometricManager.from(activity)
        val authenticators = BIOMETRIC_STRONG

        val promptInfo = PromptInfo.Builder()
            .setTitle(title)
            .setDescription(description)
            .setAllowedAuthenticators(authenticators)
            .setNegativeButtonText(
                activity.getString(R.string.auth_passcode_fingerprint_use_passcode)
            )

        when (manager.canAuthenticate(authenticators)) {
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                resultChannel.trySend(BiometricResult.HardwareUnavailable)
                return
            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                resultChannel.trySend(BiometricResult.FeatureUnavailable)
                return
            }

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                resultChannel.trySend(BiometricResult.AuthenticationNotSet)
                return
            }

            else -> Unit
        }

        GlobalScope.launch(Dispatchers.Main) {
            val prompt = BiometricPrompt(
                activity,
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        super.onAuthenticationError(errorCode, errString)
                        resultChannel.trySend(BiometricResult.AuthenticationError(errString.toString()))
                    }

                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        resultChannel.trySend(BiometricResult.AuthenticationSuccess)
                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        resultChannel.trySend(BiometricResult.AuthenticationFailed)
                    }
                }
            )
            prompt.authenticate(promptInfo.build())
        }
    }

    sealed interface BiometricResult {
        data object HardwareUnavailable : BiometricResult
        data object FeatureUnavailable : BiometricResult
        data class AuthenticationError(val error: String) : BiometricResult
        data object AuthenticationFailed : BiometricResult
        data object AuthenticationSuccess : BiometricResult
        data object AuthenticationNotSet : BiometricResult
    }
}