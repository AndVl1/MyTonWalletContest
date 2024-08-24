package ru.andvl.mytonwallet.contest.auth.impl.setpasscode.utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

fun vibrateOnKeyboardButtonClick(context: Context) {
    val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager = context.getSystemService(VibratorManager::class.java)
        vibratorManager?.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
    }

    if (vibrator != null && vibrator.hasVibrator()) {
        val vibrationEffect = VibrationEffect.createOneShot(50L, 75)

        vibrator.vibrate(vibrationEffect)
    }
}