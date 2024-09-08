package ru.andvl.mytonwallet.contest.root.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pushToFront
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ru.andvl.mytonwallet.contest.auth.api.AuthDecomposeComponent
import ru.andvl.mytonwallet.contest.auth.api.AuthLaunchType
import ru.andvl.mytonwallet.contest.bottombar.api.BottomBarDecomposeComponent
import ru.andvl.mytonwallet.contest.datastore.UserSettingsRepository
import ru.andvl.mytonwallet.contest.decompose.DecomposeComponent
import ru.andvl.mytonwallet.contest.decompose.DecomposeOnBackParameter
import ru.andvl.mytonwallet.contest.decompose.popOr
import ru.andvl.mytonwallet.contest.root.api.RootDecomposeComponent
import ru.andvl.mytonwallet.contest.root.api.model.RootScreenConfig

class RootDecomposeComponentImpl(
    componentContext: ComponentContext,
    private val userSettingsRepository: UserSettingsRepository,
    private val onBack: DecomposeOnBackParameter,
    private val authFactory: AuthDecomposeComponent.Factory,
    private val bottomBarFactory: BottomBarDecomposeComponent.Factory
) : RootDecomposeComponent, ComponentContext by componentContext {
    init {
        coroutineScope().launch {
            getDefaultStack()
        }
    }

    private val navigation = StackNavigation<RootScreenConfig>()
    private val stack: Value<ChildStack<RootScreenConfig, DecomposeComponent>> = childStack(
        source = navigation,
        serializer = RootScreenConfig.serializer(),
        initialStack = { listOf(RootScreenConfig.Loading) },
        handleBackButton = true,
        childFactory = ::child
    )

    @Composable
    override fun Render(modifier: Modifier) {
        val childStack by stack.subscribeAsState()

        Children(modifier = modifier, stack = childStack) {
            it.instance.Render()
        }
    }

    override fun push(config: RootScreenConfig) {
        navigation.pushToFront(config)
    }

    private fun child(
        rootScreenConfig: RootScreenConfig,
        componentContext: ComponentContext
    ): DecomposeComponent {
        return when (rootScreenConfig) {
            RootScreenConfig.Loading -> LoadingDecomposeComponentImpl(
                componentContext,
            )

            RootScreenConfig.AddWallet -> authFactory.invoke(
                componentContext,
                AuthLaunchType.Empty,
                ::internalOnBack,
                ::navigateToMain
            )

            RootScreenConfig.Login -> authFactory.invoke(
                componentContext,
                AuthLaunchType.Passcode,
                ::internalOnBack,
                ::navigateToMain
            )

            RootScreenConfig.Main -> bottomBarFactory.invoke(
                componentContext,
                ::internalOnBack
            )
        }
    }

    private suspend fun getDefaultStack() {
        val isSignedIn = userSettingsRepository.getWalletAccountId().first().isNotEmpty()

        if (isSignedIn) {
            navigation.replaceAll(RootScreenConfig.Login)
        } else {
            navigation.replaceAll(RootScreenConfig.AddWallet)
        }
    }

    private fun internalOnBack() {
        navigation.popOr(onBack::invoke)
    }

    private fun navigateToMain() {
        navigation.replaceCurrent(RootScreenConfig.Main)
    }

    class Factory(
        private val userSettingsRepository: UserSettingsRepository,
        private val authFactory: AuthDecomposeComponent.Factory,
        private val bottomBarFactory: BottomBarDecomposeComponent.Factory
    ) : RootDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            onBack: DecomposeOnBackParameter
        ): RootDecomposeComponent = RootDecomposeComponentImpl(
            componentContext,
            userSettingsRepository,
            onBack,
            authFactory,
            bottomBarFactory
        )
    }
}