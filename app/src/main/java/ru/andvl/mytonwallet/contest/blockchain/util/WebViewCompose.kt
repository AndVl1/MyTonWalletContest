package ru.andvl.mytonwallet.contest.blockchain.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.getKoin

@Composable
fun WebViewCompose(
    modifier: Modifier = Modifier,
    webViewHolder: WebViewHolder = getKoin().get()
) {
    DisposableEffect(Unit) {
        onDispose {
            webViewHolder.destroy()
        }
    }

    Box(Modifier.fillMaxSize().height(0.dp))
}