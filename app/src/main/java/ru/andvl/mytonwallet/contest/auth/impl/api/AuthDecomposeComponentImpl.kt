package ru.andvl.mytonwallet.contest.auth.impl.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushToFront
import com.arkivanov.decompose.value.Value
import ru.andvl.mytonwallet.contest.auth.api.AuthDecomposeComponent
import ru.andvl.mytonwallet.contest.auth.api.AuthLaunchType
import ru.andvl.mytonwallet.contest.auth.impl.model.AuthNavigationConfig
import ru.andvl.mytonwallet.contest.decompose.DecomposeComponent
import ru.andvl.mytonwallet.contest.decompose.DecomposeOnBackParameter
import ru.andvl.mytonwallet.contest.setuppasscode.api.SetUpPasscodeDecomposeComponent

class AuthDecomposeComponentImpl(
    componentContext: ComponentContext,
    launchType: AuthLaunchType,
    private val navigateToMain: () -> Unit,
    private val setUpPasscodeFactory: SetUpPasscodeDecomposeComponent.Factory
) : AuthDecomposeComponent<AuthNavigationConfig>(), ComponentContext by componentContext {

    override val stack: Value<ChildStack<AuthNavigationConfig, DecomposeComponent>> =
        childStack(
            source = navigation,
            serializer = AuthNavigationConfig.serializer(),
            initialConfiguration = getInitialConfiguration(launchType),
            handleBackButton = true,
            childFactory = ::child
        )

    private fun child(
        config: AuthNavigationConfig,
        componentContext: ComponentContext
    ): DecomposeComponent = when (config) {
        is AuthNavigationConfig.StartDestination -> startDestinationItem(config, componentContext)
        is AuthNavigationConfig.WalletCreatedFlow -> walletCreatedFlow(config, componentContext)
        is AuthNavigationConfig.WalletImportFlow -> walletImportFlow(config, componentContext)
    }

    private fun startDestinationItem(
        config: AuthNavigationConfig.StartDestination,
        componentContext: ComponentContext
    ): DecomposeComponent = when (config) {
        AuthNavigationConfig.NoWalletScreen -> NoCurrentWalletDecomposeComponentImpl(
            componentContext,
            navigation
        )

        AuthNavigationConfig.PasscodeScreen -> PasscodeDecomposeComponentImpl(
            componentContext,
            navigateToMain
        )
    }

    private fun walletCreatedFlow(
        config: AuthNavigationConfig.WalletCreatedFlow,
        componentContext: ComponentContext
    ): DecomposeComponent = when (config) {
        is AuthNavigationConfig.WalletCreatedStartScreen -> WalletCreatedStartDecomposeComponentImpl(
            componentContext,
            navigation
        )

        is AuthNavigationConfig.WalletCreatedSetUpPasscode -> setUpPasscodeFactory(
            componentContext,
            onBack = { navigation.pop() },
            navigateNext = {
                navigation.pushToFront(AuthNavigationConfig.RecoveryListScreen(it))
            },
            isImport = false,
            mnemonic = null
        )

        is AuthNavigationConfig.RecoveryListScreen -> RecoveryListDecomposeComponentImpl(
            componentContext,
            navigation,
            config.passcode
        )

        is AuthNavigationConfig.RecoveryTestScreen -> RecoveryTestDecomposeComponentImpl(
            componentContext,
            navigation,
            config.recoveryWords,
            config.passcode,
            navigateToMain
        )
    }

    private fun walletImportFlow(
        config: AuthNavigationConfig.WalletImportFlow,
        componentContext: ComponentContext
    ): DecomposeComponent = when (config) {
        is AuthNavigationConfig.WalletImportScreen -> WalletImportDecomposeComponentImpl(
            componentContext,
            navigation
        )

        is AuthNavigationConfig.WalletImportSetUpPasscode -> setUpPasscodeFactory(
            componentContext,
            onBack = { navigation.pop() },
            navigateNext = { navigateToMain() },
            isImport = true,
            mnemonic = config.mnemonic
        )
    }

    private fun getInitialConfiguration(launchType: AuthLaunchType): AuthNavigationConfig {
        return when (launchType) {
            AuthLaunchType.Passcode -> AuthNavigationConfig.PasscodeScreen
            AuthLaunchType.Empty -> AuthNavigationConfig.NoWalletScreen
        }
    }

    class Factory(
        private val setUpPasscodeFactory: SetUpPasscodeDecomposeComponent.Factory
    ) : AuthDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            launchType: AuthLaunchType,
            onBack: DecomposeOnBackParameter,
            navigateToMain: () -> Unit,
        ): AuthDecomposeComponent<*> = AuthDecomposeComponentImpl(
            componentContext,
            launchType,
            navigateToMain,
            setUpPasscodeFactory
        )
    }
}
