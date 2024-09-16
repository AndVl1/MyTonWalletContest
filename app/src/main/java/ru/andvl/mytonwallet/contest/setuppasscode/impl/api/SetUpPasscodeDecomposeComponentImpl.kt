package ru.andvl.mytonwallet.contest.setuppasscode.impl.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import ru.andvl.mytonwallet.contest.decompose.DecomposeComponent
import ru.andvl.mytonwallet.contest.decompose.DecomposeOnBackParameter
import ru.andvl.mytonwallet.contest.setuppasscode.api.SetUpPasscodeDecomposeComponent
import ru.andvl.mytonwallet.contest.setuppasscode.impl.model.SetUpPasscodeNavigationConfig

class SetUpPasscodeDecomposeComponentImpl(
    componentContext: ComponentContext,
    private val navigateNext: (passcode: String) -> Unit,
    private val isImport: Boolean,
    private val mnemonic: List<String>?,
) : SetUpPasscodeDecomposeComponent<SetUpPasscodeNavigationConfig>(),
    ComponentContext by componentContext {
    override val stack: Value<ChildStack<SetUpPasscodeNavigationConfig, DecomposeComponent>> =
        childStack(
            source = navigation,
            serializer = SetUpPasscodeNavigationConfig.serializer(),
            initialConfiguration = SetUpPasscodeNavigationConfig.SetPasscodeScreen,
            handleBackButton = true,
            childFactory = ::child
        )

    private fun child(
        config: SetUpPasscodeNavigationConfig,
        componentContext: ComponentContext
    ): DecomposeComponent = when (config) {
        is SetUpPasscodeNavigationConfig.SetPasscodeScreen -> SetPasscodeDecomposeComponentImpl(
            componentContext,
            navigation
        )

        is SetUpPasscodeNavigationConfig.ConfirmPasscodeScreen -> ConfirmPasscodeDecomposeComponentImpl(
            componentContext,
            navigation,
            config.correctPasscode,
            config.passcodeLength
        )

        is SetUpPasscodeNavigationConfig.BiometricLockScreen -> BiometricLockDecomposeComponentImpl(
            componentContext,
            config.passcode,
            mnemonic,
            isImport,
            navigateNext
        )
    }

    class Factory : SetUpPasscodeDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            onBack: DecomposeOnBackParameter,
            navigateNext: (passcode: String) -> Unit,
            isImport: Boolean,
            mnemonic: List<String>?
        ): SetUpPasscodeDecomposeComponent<*> =
            SetUpPasscodeDecomposeComponentImpl(componentContext, navigateNext, isImport, mnemonic)
    }
}
