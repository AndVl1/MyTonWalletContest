package ru.andvl.mytonwallet.contest.setuppasscode.impl.api

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import org.koin.compose.koinInject
import ru.andvl.mytonwallet.contest.arch.core.viewModelWithFactory
import ru.andvl.mytonwallet.contest.auth.impl.biometriclock.BiometricLockViewModel
import ru.andvl.mytonwallet.contest.auth.impl.biometriclock.CreateBiometricLockScreen
import ru.andvl.mytonwallet.contest.blockchain.api.BlockchainRepository
import ru.andvl.mytonwallet.contest.decompose.ScreenDecomposeComponent

class BiometricLockDecomposeComponentImpl(
    componentContext: ComponentContext,
    private val passcode: String,
    private val mnemonic: List<String>? = null,
    private val isImport: Boolean,
    private val navigateNext: (passcode: String) -> Unit
) : ScreenDecomposeComponent(componentContext) {
    @Composable
    override fun Render() {
        val blockchainRepository: BlockchainRepository = koinInject()
        val viewModel = viewModelWithFactory(null) {
            BiometricLockViewModel(passcode, mnemonic, isImport, blockchainRepository)
        }
        CreateBiometricLockScreen(
            navigateNext = { navigateNext(passcode) },
            viewModel = viewModel
        )
    }
}