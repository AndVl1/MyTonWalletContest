package ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme
import kotlin.math.max

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletScreenTopBar(
    scrollState: LazyListState,
    onSettingsClicked: () -> Unit,
    onScanClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val topBarHeight = 64.dp
    val toolbarHeightPx = with(LocalDensity.current) { topBarHeight.toPx() }
    val titleOffset by remember {
        derivedStateOf {
            val offset = scrollState.firstVisibleItemScrollOffset.toFloat()
            val index = scrollState.firstVisibleItemIndex
            when {
                index > 0 -> 0f
                else -> max(toolbarHeightPx - offset, 0f)
            }
        }
    }

    CenterAlignedTopAppBar(
        title = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .graphicsLayer { translationY = titleOffset }
            ) {
                Text(
                    text = "MyTonWallet",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Text(
                    text = "\$12 345.67",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = onSettingsClicked) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_settings),
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(onClick = onScanClicked) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_scan),
                    contentDescription = null
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors().copy(
            containerColor = MaterialTheme.colorScheme.background,
            scrolledContainerColor = MaterialTheme.colorScheme.background,
            navigationIconContentColor = MaterialTheme.colorScheme.tertiary,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            actionIconContentColor = MaterialTheme.colorScheme.tertiary,
        ),
        windowInsets = WindowInsets(0.dp),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun WalletScreenTopBarPreview() {
    MyTonWalletContestTheme {
        WalletScreenTopBar(
            scrollState = rememberLazyListState(),
            onSettingsClicked = {},
            onScanClicked = {},
        )
    }
}