package ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ListItemBoxWithDivider(
    modifier: Modifier = Modifier,
    hasDivider: Boolean = false,
    dividerPaddingValues: PaddingValues = PaddingValues(start = 76.dp),
    content: @Composable (BoxScope.() -> Unit)
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomStart
    ) {
        content()
        if (hasDivider) ListItemDivider(paddingValues = dividerPaddingValues)
    }
}