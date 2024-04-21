package com.example.parkirapp.ui.screens.layout

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.parkirapp.data.vm.ReservationModel
import com.example.parkirapp.ui.navigation.BottomNavBarItem
import com.example.parkirapp.ui.navigation.Destination
import com.example.parkirapp.ui.navigation.Navigation

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LayoutScreen(reservationModel: ReservationModel) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = listOf(
                    BottomNavBarItem.Map,
                    BottomNavBarItem.Bookings,
                    BottomNavBarItem.Notifications,
                    BottomNavBarItem.Profile,
                ),
                navController = navController,
            ) { item ->
                navController.navigate(item.route)
            }
        },
        modifier = Modifier.background(Color.Red)
    ) {
        Navigation(navController = navController, startDestination = Destination.Map, reservationModel = reservationModel)
    }
}


@Composable
fun BottomNavigationBar(
    items: List<BottomNavBarItem>,
    navController: NavController,
    onItemClick: (BottomNavBarItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    Box(
        modifier = Modifier.shadow(
            elevation = 20.dp,
            shape = RoundedCornerShape(20.dp),
            spotColor = Color.Black,
            ambientColor = Color.Black,
        )
    ) {
        NavigationBar(
            containerColor = Color.White,
            tonalElevation = 0.dp,
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
        ) {
            items.forEach { item ->
                val selected = item.route == backStackEntry.value?.destination?.route
                NavigationBarItem(
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        unselectedIconColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                        indicatorColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                    ),
                    icon = {
                        if (item.icon2 != null)
                            Icon(
                                painter = painterResource(id = item.icon2),
                                contentDescription = item.label,
                                modifier = Modifier.size(25.dp)
                            )
                        else if (item.icon != null)
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label,
                                modifier = Modifier.size(25.dp)
                            )
                    },
                    label = {
                        Text(text = item.label, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                    },
                    selected = selected,
                    onClick = {
                        onItemClick(item)
                    },
                )
            }
        }
    }
}
