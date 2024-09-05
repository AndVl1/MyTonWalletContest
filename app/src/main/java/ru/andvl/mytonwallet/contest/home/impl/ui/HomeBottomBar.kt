package ru.andvl.mytonwallet.contest.home.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeBottomBar(modifier: Modifier = Modifier) {

}

//@Composable
//fun RepetonBottomBar(
//    tabs: Array<BottomBarScreen>,
//    navController: NavController,
//) {
//    val backStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = backStackEntry?.destination
//    val bottomBarDestination = tabs.any { it.route == currentDestination?.route }
//
//    if (bottomBarDestination) {
//        BottomAppBar(
//            modifier = Modifier.height(55.dp),
//            tonalElevation = 1.dp,
//            contentPadding = PaddingValues(0.dp)
//        ) {
//            NavigationBar(
//                containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
//            ) {
//                tabs.forEach { item ->
//                    NavigationBarItem(
//                        selected = currentDestination?.hierarchy?.any {
//                            it.route == item.route
//                        } == true,
//                        onClick = {
//                            navController.navigate(item.route) {
//                                popUpTo(navController.graph.findStartDestination().id) {
//                                    saveState = true
//                                }
//                                launchSingleTop = true
//                                restoreState = true
//                            }
//                        },
//                        icon = {
//                            Icon(
//                                imageVector = ImageVector.vectorResource(item.icon),
//                                contentDescription = null,
//                                modifier = Modifier.size(24.dp)
//                            )
//                        },
//                        colors = NavigationBarItemDefaults.colors(
//                            selectedIconColor = MaterialTheme.colorScheme.onSurface,
//                            unselectedIconColor = MaterialTheme.colorScheme.outline,
//                            indicatorColor = MaterialTheme.colorScheme.surfaceContainerHigh
//                        )
//                    )
//                }
//            }
//        }
//    }
//}