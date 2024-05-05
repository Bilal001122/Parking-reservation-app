package com.example.parkirapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.parkirapp.data.api.vm.LoginVM
import com.example.parkirapp.data.api.vm.ParkingsVM
import com.example.parkirapp.data.api.vm.RegistrationVM
import com.example.parkirapp.data.api.vm.ReservationVM
import com.example.parkirapp.ui.screens.bookings.BookingsScreen
import com.example.parkirapp.ui.screens.favorites.FavoritesScreen
import com.example.parkirapp.ui.screens.layout.LayoutScreen
import com.example.parkirapp.ui.screens.login.LoginScreen
import com.example.parkirapp.ui.screens.map.MapScreen
import com.example.parkirapp.ui.screens.on_boarding.OnBoardingScreen
import com.example.parkirapp.ui.screens.parking_details.ParkingDetailsScreen
import com.example.parkirapp.ui.screens.parkings_list.ParkingListScreen
import com.example.parkirapp.ui.screens.payment.PaymentScreen
import com.example.parkirapp.ui.screens.profile.ProfileScreen
import com.example.parkirapp.ui.screens.reservation.ReservationScreen
import com.example.parkirapp.ui.screens.signup.SignUpScreen

@Composable
fun Navigation(
    navController: NavHostController,
    startDestination: Destination,
    reservationModel: ReservationVM,
    registrationVM: RegistrationVM,
    loginVM: LoginVM,
    parkingsVM: ParkingsVM
) {
    NavHost(navController = navController, startDestination = startDestination.route) {
        composable(Destination.OnBoarding.route) {
            OnBoardingScreen(navController)
        }
        composable(Destination.Login.route) {
            LoginScreen(navController, loginVM)
        }
        composable(Destination.ParkingList.route) {
            ParkingListScreen(navController, parkingsVM)
        }

        composable(Destination.SignUp.route) {
            SignUpScreen(navController, registrationVM)
        }
        composable(Destination.ParkingDetails.route) {
            val parkingId = it.arguments?.getString("parkingId")?.toInt()
            ParkingDetailsScreen(parkingId, navController, parkingsVM)
        }
        composable(Destination.Reservation.route) {
            val parkingId = it.arguments?.getString("parkingId")?.toInt()
            ReservationScreen(parkingId, navController)
        }
        composable(Destination.Layout.route) {
            LayoutScreen(
                reservationModel = reservationModel,
                registrationVM = registrationVM,
                loginVM = loginVM,
                parkingsVM = parkingsVM
            )
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
        composable(Destination.ParkingList.route) {
            ParkingListScreen(navController = navController, parkingsVM = parkingsVM)
        }
        composable(Destination.Payment.route) {
            PaymentScreen(
                navController = navController
            )
        }
    }
}