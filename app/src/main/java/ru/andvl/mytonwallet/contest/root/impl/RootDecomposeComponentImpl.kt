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
import com.arkivanov.decompose.value.Value
import ru.andvl.mytonwallet.contest.auth.api.AuthDecomposeComponent
import ru.andvl.mytonwallet.contest.auth.api.AuthLaunchType
import ru.andvl.mytonwallet.contest.decompose.DecomposeComponent
import ru.andvl.mytonwallet.contest.decompose.DecomposeOnBackParameter
import ru.andvl.mytonwallet.contest.decompose.popOr
import ru.andvl.mytonwallet.contest.root.api.RootDecomposeComponent
import ru.andvl.mytonwallet.contest.root.api.model.RootScreenConfig

class RootDecomposeComponentImpl(
    componentContext: ComponentContext,
    private val onBack: DecomposeOnBackParameter,
    private val authFactory: AuthDecomposeComponent.Factory
) : RootDecomposeComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<RootScreenConfig>()
    private val stack: Value<ChildStack<RootScreenConfig, DecomposeComponent>> = childStack(
        source = navigation,
        serializer = RootScreenConfig.serializer(),
        initialStack = { getDefaultStack() },
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
            RootScreenConfig.AddWallet -> authFactory.invoke(componentContext, AuthLaunchType.Empty, ::internalOnBack)
            RootScreenConfig.Login -> authFactory.invoke(componentContext, AuthLaunchType.Passcode, ::internalOnBack)
        }
    }

    private fun getDefaultStack(): List<RootScreenConfig> {
        // todo check auth status
        return listOf(RootScreenConfig.AddWallet)
    }

    private fun internalOnBack() {
        navigation.popOr(onBack::invoke)
    }

    class Factory(
        private val authFactory: AuthDecomposeComponent.Factory
    ) : RootDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            onBack: DecomposeOnBackParameter
        ): RootDecomposeComponent = RootDecomposeComponentImpl(componentContext, onBack, authFactory)
    }
}