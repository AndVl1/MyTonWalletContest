package ru.andvl.mytonwallet.contest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.decompose.extensions.compose.stack.animation.LocalStackAnimationProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.androidx.compose.koinViewModel
import ru.andvl.mytonwallet.contest.blockchain.api.BlockchainRepository
import ru.andvl.mytonwallet.contest.root.api.LocalRootNavigation
import ru.andvl.mytonwallet.contest.root.api.RootDecomposeComponent
import ru.andvl.mytonwallet.contest.ui.components.TonWalletButton
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme
import ru.andvl.mytonwallet.contest.ui.utils.WalletStackAnimationProvider

class MainActivity : ComponentActivity() {
    private val rootComponentFactory: RootDecomposeComponent.Factory = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val root = rootComponentFactory.invoke(defaultComponentContext(), this::finish)
        setContent {
            MyTonWalletContestTheme {
                CompositionLocalProvider(
                    LocalRootNavigation provides root,
                    LocalStackAnimationProvider provides WalletStackAnimationProvider
                ) {
//                    root.Render(
//                        Modifier.fillMaxSize()
//                            .imePadding()
//                    )
                    Test(
                        modifier = Modifier
                            .fillMaxSize()
                            .imePadding()
                    )
                }
            }
        }
    }
}

class BlockchainViewModel(
    private val blockchainRepository: BlockchainRepository
) : ViewModel() {
    private val _state = MutableStateFlow(emptyList<String>())
    val state = _state.asStateFlow()

    fun onClicked() {
        viewModelScope.launch {
            val result = blockchainRepository.getMnemonicWordList()
            Log.d("BlockchainViewModel", "result: $result")
            _state.update { result }
        }
    }
}

@Composable
fun Test(modifier: Modifier = Modifier) {
    val viewModel: BlockchainViewModel = koinViewModel()
    val state = viewModel.state.collectAsState().value

    // Collect the flow as state, with an initial empty list
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