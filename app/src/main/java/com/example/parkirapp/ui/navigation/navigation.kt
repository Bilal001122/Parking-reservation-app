package com.example.parkirapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.parkirapp.data.vm.ReservationModel
import com.example.parkirapp.ui.screens.bookings.BookingsScreen
import com.example.parkirapp.ui.screens.favorites.FavoritesScreen
import com.example.parkirapp.ui.screens.layout.LayoutScreen
import com.example.parkirapp.ui.screens.login.LoginScreen
import com.example.parkirapp.ui.screens.map.MapScreen
import com.example.parkirapp.ui.screens.notifications.NotificationsScreen
import com.example.parkirapp.ui.screens.on_boarding.OnBoardingScreen
import com.example.parkirapp.ui.screens.parking_details.ParkingDetailsScreen
import com.example.parkirapp.ui.screens.parkings_list.ParkingListScreen
import com.example.parkirapp.ui.screens.profile.ProfileScreen
import com.example.parkirapp.ui.screens.reservation.ReservationScreen
import com.example.parkirapp.ui.screens.signup.SignUpScreen

@Composable
fun Navigation(navController: NavHostController, startDestination: Destination, reservationModel: ReservationModel) {
    NavHost(navController = navController, startDestination = startDestination.route) {
        composable(Destination.OnBoarding.route) {
            OnBoardingScreen(navController)
        }
        composable(Destination.Login.route) {
            LoginScreen(navController)
        }
        composable(Destination.ParkingList.route) {
            ParkingListScreen(navController)
        }

        composable(Destination.SignUp.route) {
            SignUpScreen(navController)
        }
        composable(Destination.ParkingDetails.route) {
            val parkingId = it.arguments?.getString("parkingId")?.toInt()
            ParkingDetailsScreen(parkingId, navController)
        }
        composable(Destination.Reservation.route) {
            val parkingId = it.arguments?.getString("parkingId")?.toInt()
            ReservationScreen(parkingId, navController)
        }
        composable(Destination.Layout.route) {
            LayoutScreen(reservationModel = reservationModel)
        }
        composable(Destination.Map.route) {
            MapScreen(navController = navController)
        }
        composable(Destination.Favorites.route) {
            FavoritesScreen(navController = navController)
        }
        composable(Destination.Profile.route) {
            ProfileScreen(navController = navController)
        }
        composable(Destination.Bookings.route) {
            BookingsScreen(navController = navController, reservationModel = reservationModel)
        }
        composable(Destination.Notifications.route) {
            NotificationsScreen(navController = navController)
        }
    }
}