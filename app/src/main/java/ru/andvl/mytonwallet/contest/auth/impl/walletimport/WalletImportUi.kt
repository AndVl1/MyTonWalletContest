package ru.andvl.mytonwallet.contest.auth.impl.walletimport

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel

@Composable
fun WalletImportScreen(
    modifier: Modifier = Modifier,
    viewModel: WalletImportViewModel = koinViewModel()
) {
    Box(modifier.fillMaxSize()) {
        Text("WALLET IMPORT", fontSize = 48.sp, color = Color.Black)
    }
}
