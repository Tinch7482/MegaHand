package com.example.megahand

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import java.lang.reflect.Modifier

sealed class Screens(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null
) {
    object HomeScreen : Screens(
        route = "home_screen",
        title = "Главная",
        icon = Icons.Outlined.Home
    )

    object Favourite : Screens(
        route = "favourite_screen",
        title = "Favorite",
        icon = Icons.Outlined.Favorite
    )

    object Notification : Screens(
        route = "notification_screen",
        title = "Notification",
        icon = Icons.Outlined.Notifications
    )

}
//@Composable
//fun NavigationGraph(navController: NavHostController) {
//    NavHost(navController, startDestination = Screens.HomeScreen.route) {
//        composable(Screens.HomeScreen.route) {
//            Menu()
//        }
//        composable(Screens.Favourite.route) {
//            FavouriteScreen()
//        }
//        composable(Screens.Notification.route) {
//            NotificationScreen()
//        }
//    }
//}
@Composable
fun BottomBar(
    navController: NavHostController, state: MutableState<Boolean>
) {
    val screens = listOf(
        Screens.HomeScreen, Screens.Favourite, Screens.Notification
    )

    NavigationBar(
        containerColor = Color.White,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        screens.forEach { screen ->

            NavigationBarItem(
                label = {
                    Text(text = screen.title!!)
                },
                icon = {
                    Icon(imageVector = screen.icon!!, contentDescription = "")
                },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    unselectedTextColor = Color.Gray, selectedTextColor = Color.White
                ),
            )
        }
    }

}