package ru.andvl.mytonwallet.contest.bottombar.impl.wallet.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.ui.theme.ListDividerColor
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme
import ru.andvl.mytonwallet.contest.utils.USD_SYMBOL
import ru.andvl.mytonwallet.contest.utils.formatBalanceOrTransactionAmount

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletScreenTopBar(
    balance: Float,
    currencySymbol: String,
    scrollProgress: Float,
    onSettingsClicked: () -> Unit,
    onScanClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val topBarBackgroundColor = MaterialTheme.colorScheme.background.copy(
        alpha = if (scrollProgress >= 1f) 1f else 0.0f
    )

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        CenterAlignedTopAppBar(
            title = {
                AnimatedVisibility(
                    visible = scrollProgress >= 1f,
                    enter = fadeIn() + expandIn(
                        expandFrom = Alignment.Center
                    ),
                    exit = fadeOut()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.main_wallet_title),
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                        Text(
                            text = "${currencySymbol}${formatBalanceOrTransactionAmount(balance)}",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
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
            colors = TopAppBarDefaults.topAppBarColors().copy(
                containerColor = topBarBackgroundColor,
                scrolledContainerColor = MaterialTheme.colorScheme.background,
                navigationIconContentColor = MaterialTheme.colorScheme.tertiary,
                titleContentColor = MaterialTheme.colorScheme.onBackground,
                actionIconContentColor = MaterialTheme.colorScheme.tertiary,
            ),
            windowInsets = WindowInsets(0.dp)
        )

        AnimatedVisibility(visible = scrollProgress >= 1f) {
            HorizontalDivider(
                thickness = 0.5.dp,
                color = ListDividerColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WalletScreenTopBarPreview() {
    MyTonWalletContestTheme {
        WalletScreenTopBar(
            balance = 12345.67f,
            currencySymbol = USD_SYMBOL,
            scrollProgress = 1f,
            onSettingsClicked = {},
            onScanClicked = {},
        )
    }
}