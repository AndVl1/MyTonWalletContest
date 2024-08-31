package ru.andvl.mytonwallet.contest.auth.impl.walletimport

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import ru.andvl.mytonwallet.contest.ui.components.TonWalletButton

@Composable
fun WalletImportScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: BlockchainViewModel = koinViewModel()
    val state = viewModel.state.collectAsState().value

    Column(modifier) {
        TonWalletButton(
            onClick = viewModel::onClicked,
            text = "Click",
        )
        if (state.isNotEmpty()) {
            LazyColumn {
                items(state) {
                    Text(text = it)
                }
            }
        } else {
            Text("empty")
        }
    }
}
