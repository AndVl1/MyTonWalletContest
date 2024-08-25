package ru.andvl.mytonwallet.contest.arch.core

import androidx.annotation.CallSuper
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import kotlinx.coroutines.SupervisorJob
import ru.andvl.mytonwallet.contest.coroutines.WalletDispatchers
import ru.andvl.mytonwallet.contest.lifecycle.DecomposeViewModelCoroutineScopeProvider

abstract class DecomposeViewModel : InstanceKeeper.Instance, LifecycleOwner {
    private val registry by lazy { LifecycleRegistry(Lifecycle.State.INITIALIZED) }
    override val lifecycle = registry

    protected val viewModelScope = DecomposeViewModelCoroutineScopeProvider.provideCoroutineScope(
        lifecycleOwner = this@DecomposeViewModel,
        context = SupervisorJob() + WalletDispatchers.workStealingDispatcher
    )

    init {
        registry.onCreate()
        registry.onStart()
        registry.onResume()
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        registry.onPause()
        registry.onStop()
        registry.onDestroy()
    }
}
