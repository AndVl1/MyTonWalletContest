package ru.andvl.mytonwallet.contest.auth.impl.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import ru.andvl.mytonwallet.contest.auth.api.AuthDecomposeComponent
import ru.andvl.mytonwallet.contest.auth.api.AuthLaunchType
import ru.andvl.mytonwallet.contest.auth.impl.model.AuthNavigationConfig
import ru.andvl.mytonwallet.contest.decompose.DecomposeComponent
import ru.andvl.mytonwallet.contest.decompose.DecomposeOnBackParameter

class AuthDecomposeComponentImpl(
    componentContext: ComponentContext,
    launchType: AuthLaunchType
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
            navigation
        )
    }

    private fun walletCreatedFlow(
        config: AuthNavigationConfig.WalletCreatedFlow,
        componentContext: ComponentContext
    ): DecomposeComponent = when (config) {
        is AuthNavigationConfig.BiometricLockScreen -> TODO()
        is AuthNavigationConfig.RecoveryListScreen -> TODO()
        is AuthNavigationConfig.RecoveryTestScreen -> TODO()
        is AuthNavigationConfig.SetPasswordScreen -> TODO()
        is AuthNavigationConfig.WalletCreatedStartScreen -> WalletCreatedStartDecomposeComponentImpl(
            componentContext,
            navigation
        )
    }

    private fun walletImportFlow(
        config: AuthNavigationConfig.WalletImportFlow,
        componentContext: ComponentContext
    ): DecomposeComponent = when (config) {
        is AuthNavigationConfig.WalletImportScreen -> WalletImportDecomposeComponentImpl(
            componentContext
        )
    }

    private fun getInitialConfiguration(launchType: AuthLaunchType): AuthNavigationConfig {
        return when (launchType) {
            AuthLaunchType.Passcode -> AuthNavigationConfig.PasscodeScreen
            AuthLaunchType.Empty -> AuthNavigationConfig.NoWalletScreen
        }
    }

    class Factory : AuthDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            launchType: AuthLaunchType,
            onBack: DecomposeOnBackParameter
        ): AuthDecomposeComponent<*> = AuthDecomposeComponentImpl(componentContext, launchType)
    }
}
