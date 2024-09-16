package ru.andvl.mytonwallet.contest

import android.graphics.Color
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.decompose.extensions.compose.stack.animation.LocalStackAnimationProvider
import org.koin.android.ext.android.get
import org.koin.compose.koinInject
import ru.andvl.mytonwallet.contest.blockchain.api.BlockchainRepository
import ru.andvl.mytonwallet.contest.root.api.LocalRootNavigation
import ru.andvl.mytonwallet.contest.root.api.RootDecomposeComponent
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme
import ru.andvl.mytonwallet.contest.ui.utils.WalletStackAnimationProvider

class MainActivity : AppCompatActivity() {
    private val rootComponentFactory: RootDecomposeComponent.Factory = get()

    companion object {
        var mainActivity: MainActivity? = null
        fun getInstance(): MainActivity? = mainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        mainActivity = this
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
        )
        val root = rootComponentFactory.invoke(defaultComponentContext(), this::finish)
        setContent {
            val blockchainRepository: BlockchainRepository = koinInject()
            blockchainRepository.getMnemonicCheckIndexes()

            MyTonWalletContestTheme {
                CompositionLocalProvider(
                    LocalRootNavigation provides root,
                    LocalStackAnimationProvider provides WalletStackAnimationProvider
                ) {
                    root.Render(
                        Modifier.fillMaxSize()
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mainActivity = this
    }

    override fun onRestart() {
        super.onRestart()
        mainActivity = this
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity = null
    }
}