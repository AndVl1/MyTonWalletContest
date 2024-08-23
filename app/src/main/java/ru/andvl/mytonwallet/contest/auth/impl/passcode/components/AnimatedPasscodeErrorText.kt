package ru.andvl.mytonwallet.contest.auth.impl.passcode.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedPasscodeErrorText(
    triggerError: Boolean,
    onErrorHandled: () -> Unit
) {
//    val context = LocalContext.current
    val offsetX = remember { Animatable(0f) }

    LaunchedEffect(triggerError) {
        if (triggerError) {
            // TODO vibrateOnError
//            vibrateOnError(context)
            offsetX.animateTo(
                targetValue = 0f,
                animationSpec = keyframes {
                    durationMillis = 200
                    20f at 100
                }
            )

            onErrorHandled()
        }
    }

    PasscodeLockTitleWithDescription(modifier = Modifier.offset(x = offsetX.value.dp))
}

@Preview(showBackground = true)
@Composable
fun AnimatedPasscodeErrorTextWithButtonPreview() {
    var triggerError by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AnimatedPasscodeErrorText(
            triggerError = triggerError,
            onErrorHandled = { triggerError = false }
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { triggerError = true }) {
            Text("trigger")
        }
    }
}