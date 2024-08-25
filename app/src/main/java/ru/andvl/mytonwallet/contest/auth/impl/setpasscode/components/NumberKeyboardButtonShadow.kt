package ru.andvl.mytonwallet.contest.auth.impl.setpasscode.components

import android.annotation.SuppressLint
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

@SuppressLint("ModifierFactoryUnreferencedReceiver")
@Composable
fun Modifier.numberKeyboardButtonShadow() = this.shadow(
    elevation = 4.dp,
    shape = RoundedCornerShape(10.dp),
    clip = false,
    ambientColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(
        alpha = 0.5f
    ),
    spotColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(
        alpha = 0.5f
    )
)