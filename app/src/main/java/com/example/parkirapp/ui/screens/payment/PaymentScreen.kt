package com.example.parkirapp.ui.screens.payment

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.parkirapp.ui.screens.payment.components.CardDetails


@Composable
fun PaymentScreen(
    navController: NavController
) {
    CardDetails(navController = navController)
}
