package com.example.parkirapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.parkirapp.ui.navigation.BottomNavBarItem

@Composable
fun rememberAppState(
    navHostController: NavHostController = rememberNavController()
) = remember(navHostController) {
    AppState(navHostController)
}

@Stable
class AppState(
    private val navHostController: NavHostController
) {

    private val routes = listOf<String>(
        BottomNavBarItem.Map.route,
        BottomNavBarItem.Bookings.route,
        BottomNavBarItem.Parkings.route,
        BottomNavBarItem.Profile.route
    )

    val shouldShowBottomBar: Boolean
        @Composable get() =
            navHostController.currentBackStackEntryAsState().value?.destination?.route in routes
}