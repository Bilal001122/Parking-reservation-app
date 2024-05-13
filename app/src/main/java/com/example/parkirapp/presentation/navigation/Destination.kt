package com.example.parkirapp.presentation.navigation

sealed class Destination(
    val route: String
) {
    data object OnBoarding : Destination("on_boarding")

    data object Login : Destination("login")

    data object SignUp : Destination("sign_up")

    data object ParkingList : Destination("parking_list")

    data object ParkingDetails : Destination("parking_details/{parkingId}") {
        fun createRoute(parkingId: Int) = "parking_details/$parkingId"
    }

    data object Reservation : Destination("reservation/{parkingId}") {
        fun createRoute(parkingId: Int) = "reservation/$parkingId"
    }

    data object Layout : Destination("layout")

    data object Profile : Destination("profile")

    data object Bookings : Destination("bookings")

    data object Favorites : Destination("favorites")

    data object Map : Destination("map")

    data object Payment : Destination("payment/{date}/{startTime}/{endTime}/{total}/{parkingId}") {
        fun createRoute(
            date: String,
            startTime: String,
            endTime: String,
            total: Double,
            parkingId: Int
        ) = "payment/$date/$startTime/$endTime/$total/$parkingId"
    }
}