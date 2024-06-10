package com.example.parkirapp.presentation.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.parkirapp.business_logic.vm.LoginVM
import com.example.parkirapp.business_logic.vm.ParkingsVM
import com.example.parkirapp.business_logic.vm.RegistrationVM
import com.example.parkirapp.business_logic.vm.ReservationVM
import com.example.parkirapp.presentation.screens.bookings.BookingsScreen
import com.example.parkirapp.presentation.screens.favorites.FavoritesScreen
import com.example.parkirapp.presentation.screens.layout.LayoutScreen
import com.example.parkirapp.presentation.screens.login.LoginScreen
import com.example.parkirapp.presentation.screens.map.MapScreen
import com.example.parkirapp.presentation.screens.on_boarding.OnBoardingScreen
import com.example.parkirapp.presentation.screens.parking_details.ParkingDetailsScreen
import com.example.parkirapp.presentation.screens.parkings_list.ParkingListScreen
import com.example.parkirapp.presentation.screens.payment.PaymentScreen
import com.example.parkirapp.presentation.screens.profile.ProfileScreen
import com.example.parkirapp.presentation.screens.reservation.ReservationScreen
import com.example.parkirapp.presentation.screens.signup.SignUpScreen

@Composable
fun Navigation(
    navController: NavHostController,
    startDestination: Destination,
    reservationVM: ReservationVM,
    registrationVM: RegistrationVM,
    loginVM: LoginVM,
    parkingsVM: ParkingsVM,
) {
        NavHost(navController = navController, startDestination = startDestination.route,) {
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
                ReservationScreen(parkingId, navController, parkingsVM)
            }
            composable(Destination.Layout.route) {
                LayoutScreen(
                    reservationVM = reservationVM,
                    registrationVM = registrationVM,
                    loginVM = loginVM,
                    parkingsVM = parkingsVM
                )
            }
            composable(Destination.Map.route) {
                MapScreen(navController = navController, parkingsVM = parkingsVM)
            }
            composable(Destination.Favorites.route) {
                FavoritesScreen(navController = navController, parkingsVM = parkingsVM)
            }
            composable(Destination.Profile.route) {
                ProfileScreen(navController = navController, loginVM = loginVM, parkingsVM = parkingsVM, reservationVM = reservationVM)
            }
            composable(Destination.Bookings.route) {
                BookingsScreen(navController = navController, reservationVM = reservationVM)
            }
            composable(Destination.ParkingList.route) {
                ParkingListScreen(navController = navController, parkingsVM = parkingsVM)
            }
            composable(Destination.Payment.route) {
                val date = it.arguments?.getString("date")
                val startTime = it.arguments?.getString("startTime")
                val endTime = it.arguments?.getString("endTime")
                val total = it.arguments?.getString("total")?.toDouble()
                val parkingId = it.arguments?.getString("parkingId")?.toInt()
                PaymentScreen(
                    navController = navController,
                    reservationVM = reservationVM,
                    date = date,
                    startTime = startTime,
                    endTime = endTime,
                    total = total,
                    parkingId = parkingId
                )
            }
        }
}