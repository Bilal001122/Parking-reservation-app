package com.example.parkirapp.presentation.screens.bookings

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.parkirapp.business_logic.vm.ReservationVM
import com.example.parkirapp.data.api.models.Reservation
import com.example.parkirapp.presentation.screens.bookings.components.BookingItemCanceled
import com.example.parkirapp.presentation.screens.bookings.components.BookingItemCompleted
import com.example.parkirapp.presentation.screens.bookings.components.BookingItemOnGoing

@SuppressLint("CoroutineCreationDuringComposition", "UnusedContentLambdaTargetStateParameter")
@Composable
fun BookingsScreen(navController: NavController, reservationVM: ReservationVM) {
    val context = LocalContext.current
    val pref = context.getSharedPreferences("parkir_sp", Context.MODE_PRIVATE)

    LaunchedEffect(Unit) {
        val authHeader: String? = pref.getString("token", "")
        if (authHeader != null) {
            reservationVM.getAllReservations(authHeader)
        }
    }

    val onGoingState = remember {
        mutableStateOf(true)
    }

    val completedState = remember {
        mutableStateOf(false)
    }

    val canceledState = remember {
        mutableStateOf(false)
    }

    if (reservationVM.isLoading.value) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        val bookingsListState = remember {
            mutableStateListOf<Reservation>(
            ).apply {
                addAll(reservationVM.allReservations.filter {
                    it.status == "ONGOING"
                })
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.tertiary
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                FilterChip(
                    onClick = {
                        onGoingState.value = true
                        canceledState.value = false
                        completedState.value = false
                        bookingsListState.clear()
                        bookingsListState.addAll(
                            reservationVM.allReservations.filter {
                                it.status == "ONGOING"
                            }
                        )
                    },
                    label = { Text("Ongoing", fontSize = 14.sp, fontWeight = FontWeight.Medium) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = Color.White,
                        disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                        disabledLabelColor = MaterialTheme.colorScheme.primary,
                        labelColor = MaterialTheme.colorScheme.primary,
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        borderColor = MaterialTheme.colorScheme.primary,
                        borderWidth = 2.dp,
                        selected = onGoingState.value,
                        enabled = true,
                    ),
                    selected = onGoingState.value,
                    shape = CircleShape,
                )
                FilterChip(
                    onClick = {
                        onGoingState.value = false
                        canceledState.value = false
                        completedState.value = true
                        bookingsListState.clear()
                        bookingsListState.addAll(
                            reservationVM.allReservations.filter {
                                it.status == "COMPLETED"
                            }
                        )
                    },
                    label = { Text("Completed", fontSize = 14.sp, fontWeight = FontWeight.Medium) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = Color.White,
                        disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                        disabledLabelColor = MaterialTheme.colorScheme.primary,
                        labelColor = MaterialTheme.colorScheme.primary,
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        borderColor = MaterialTheme.colorScheme.primary,
                        borderWidth = 2.dp,
                        selected = completedState.value,
                        enabled = true,
                    ),
                    selected = completedState.value,
                    shape = CircleShape,
                )
                FilterChip(
                    onClick = {
                        onGoingState.value = false
                        canceledState.value = true
                        completedState.value = false
                        bookingsListState.clear()
                        bookingsListState.addAll(
                            reservationVM.allReservations.filter {
                                it.status == "CANCELED"
                            }
                        )
                    },
                    label = { Text("Canceled", fontSize = 14.sp, fontWeight = FontWeight.Medium) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = Color.White,
                        disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                        disabledLabelColor = MaterialTheme.colorScheme.primary,
                        labelColor = MaterialTheme.colorScheme.primary,
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        borderColor = MaterialTheme.colorScheme.primary,
                        borderWidth = 2.dp,
                        selected = canceledState.value,
                        enabled = true,
                    ),
                    selected = canceledState.value,
                    shape = CircleShape,
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        MaterialTheme.colorScheme.tertiary
                    )
            ) {
                items(
                    bookingsListState.toList(),
                ) { booking ->
                        when (booking.status) {
                            "ONGOING" ->
                                BookingItemOnGoing(
                                    booking = booking,
                                    navController = navController,
                                    reservationVM = reservationVM
                                )
                            "COMPLETED" ->
                                BookingItemCompleted(
                                    booking = booking,
                                    navController = navController,
                                    reservationVM = reservationVM
                                )

                            "CANCELED" ->
                                BookingItemCanceled(
                                    booking = booking,
                                    navController = navController
                                )
                        }

                }
            }


        }
    }
}