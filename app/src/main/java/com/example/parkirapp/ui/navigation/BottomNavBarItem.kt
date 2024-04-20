package com.example.parkirapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.parkirapp.R

sealed class BottomNavBarItem(
    val route: String,
    val icon: ImageVector? = null,
    val label: String,
    val icon2: Int? = null
) {
    data object Map :
        BottomNavBarItem(route = Destination.Map.route, icon2 = R.drawable.location, label = "Map")

    data object Favorites :
        BottomNavBarItem(route = Destination.Favorites.route, Icons.Default.Favorite, "Favorites")

    data object Profile :
        BottomNavBarItem(route = Destination.Profile.route, Icons.Default.Person, "Profile")

    data object Bookings : BottomNavBarItem(
        route = Destination.Bookings.route,
        icon2 = R.drawable.booking,
        label = "Bookings"
    )

    data object Notifications :
        BottomNavBarItem(
            route = Destination.Notifications.route,
            icon2 = R.drawable.notifications,
            label = "Notifi"
        )
}