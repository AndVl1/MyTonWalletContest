package ru.andvl.mytonwallet.contest.bottombar.impl.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andvl.mytonwallet.contest.bottombar.impl.model.BottomBarEnum
import ru.andvl.mytonwallet.contest.ui.theme.ListDividerColor
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme

@Composable
fun MyTonWalletBottomBar(
    selectedTab: BottomBarEnum,
    onSelect: (BottomBarEnum) -> Unit,
    modifier: Modifier = Modifier
) {
    BottomAppBar(
        tonalElevation = 0.dp,
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.background,
        contentPadding = PaddingValues(0.dp),
        modifier = modifier
            .border(
                width = 0.5.dp,
                color = ListDividerColor
            )
    ) {
        NavigationBar(
            tonalElevation = 0.dp,
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.background,
        ) {
            BottomBarEnum.entries.forEach { bottomBarTab ->
                NavigationBarItem(
                    selected = selectedTab == bottomBarTab,
                    onClick = { onSelect(bottomBarTab) },
                    icon = {
                        Icon(
                            painter = painterResource(bottomBarTab.iconId),
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(
                            text = bottomBarTab.tabName,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.tertiary,
                        indicatorColor = MaterialTheme.colorScheme.let {
                            if (selectedTab == bottomBarTab) it.primary.copy(alpha = 0.15f)
                            else it.tertiary.copy(alpha = 0.15f)
                        }
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyTonWalletBottomBarPreview() {
    MyTonWalletContestTheme {
        MyTonWalletBottomBar(
            selectedTab = BottomBarEnum.WALLET,
            onSelect = {},
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}