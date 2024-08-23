package ru.andvl.mytonwallet.contest.auth.impl.nowallet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.andvl.mytonwallet.contest.auth.impl.model.AuthNavigationConfig

@Composable
fun NoWalletScreen(
    onCreateClicked: () -> Unit,
    onImportClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxSize()) {
        Button(onCreateClicked) {
            Text("Create New Wallet")
        }
        Button(onImportClicked) {
            Text("Import Existing Wallet")
        }
    }
}