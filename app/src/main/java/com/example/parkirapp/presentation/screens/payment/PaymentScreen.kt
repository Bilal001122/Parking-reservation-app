package com.example.parkirapp.presentation.screens.payment

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.parkirapp.business_logic.vm.ReservationVM
import com.example.parkirapp.presentation.screens.payment.components.CardDetails

@Composable
fun PaymentScreen(
    date: String?,
    startTime: String?,
    endTime: String?,
    total: Double?,
    parkingId: Int?,
    navController: NavController,
    reservationVM: ReservationVM
) {
    CardDetails(
        navController = navController,
        date = date!!,
        startTime = startTime!!,
        endTime = endTime!!,
        total = total!!,
        parkingId = parkingId!!,
        reservationVM = reservationVM
    )
}
