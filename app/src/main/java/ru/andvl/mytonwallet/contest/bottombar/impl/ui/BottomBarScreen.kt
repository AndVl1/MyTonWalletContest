package ru.andvl.mytonwallet.contest.bottombar.impl.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.router.stack.ChildStack
import ru.andvl.mytonwallet.contest.bottombar.impl.model.BottomBarConfig
import ru.andvl.mytonwallet.contest.bottombar.impl.model.BottomBarEnum
import ru.andvl.mytonwallet.contest.decompose.DecomposeComponent

@Composable
fun BottomBarScreen(
    childStack: ChildStack<BottomBarConfig, DecomposeComponent>,
    onSelect: (BottomBarEnum) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            MyTonWalletBottomBar(
                selectedTab = childStack.active.configuration.enum,
                onSelect = onSelect
            )
        }
    ) { paddingValues ->
        Box(Modifier.padding(paddingValues)) {
            Children(
                stack = childStack,
            ) {
                it.instance.Render()
            }
        }
    }
}