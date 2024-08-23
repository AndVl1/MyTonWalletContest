package ru.andvl.mytonwallet.contest.auth.impl.passcode.utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

fun vibrateOnError(context: Context) {
    val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager = context.getSystemService(VibratorManager::class.java)
        vibratorManager?.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
    }

    if (vibrator != null && vibrator.hasVibrator()) {
        val vibrationPattern = longArrayOf(0, 75, 50, 75)
        val vibrationEffect = VibrationEffect.createWaveform(vibrationPattern, -1)

        vibrator.vibrate(vibrationEffect)
    }
}