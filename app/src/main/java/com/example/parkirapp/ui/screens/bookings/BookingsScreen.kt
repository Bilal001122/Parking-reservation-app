package com.example.parkirapp.ui.screens.bookings

import android.annotation.SuppressLint
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.parkirapp.bookingsList
import com.example.parkirapp.data.database.AppDatabase
import com.example.parkirapp.data.api.models.Reservation
import com.example.parkirapp.data.api.models.ReservationStatus
import com.example.parkirapp.data.api.vm.ReservationVM
import com.example.parkirapp.ui.screens.bookings.components.BookingItemCanceled
import com.example.parkirapp.ui.screens.bookings.components.BookingItemCompleted
import com.example.parkirapp.ui.screens.bookings.components.BookingItemOnGoing

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun BookingsScreen(navController: NavController, reservationModel: ReservationVM) {
    val context = LocalContext.current
    val database = AppDatabase.buildDatabase(context)
    val parkingDao = database.getParkingDao()
    val onGoingState = remember {
        mutableStateOf(true)
    }

    val completedState = remember {
        mutableStateOf(false)
    }

    val canceledState = remember {
        mutableStateOf(false)
    }

    val bookingsListState = remember {
        mutableStateListOf<Reservation>(
        ).apply {
            addAll(bookingsList.filter {
                it.status == ReservationStatus.ONGOING
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
                        bookingsList.filter {
                            it.status == ReservationStatus.ONGOING
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
                        bookingsList.filter {
                            it.status == ReservationStatus.COMPLETED
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
                        bookingsList.filter {
                            it.status == ReservationStatus.CANCELED
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
                bookingsListState.toList()
            ) { booking ->
                if (booking.status == ReservationStatus.ONGOING)
                    BookingItemOnGoing(booking = booking, navController = navController)
                if (booking.status == ReservationStatus.COMPLETED)
                    BookingItemCompleted(booking = booking, navController = navController)
                if (booking.status == ReservationStatus.CANCELED)
                    BookingItemCanceled(booking = booking, navController = navController)
            }
        }

    }


//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.LightGray.copy(alpha = 0.15f))
//            .verticalScroll(rememberScrollState())
//    ) {
//        TextButton(
//            modifier = Modifier.fillMaxWidth().padding(10.dp),
//            colors = ButtonDefaults.buttonColors(
//                containerColor = MaterialTheme.colorScheme.primary
//            ),
//            onClick = {
//            CoroutineScope(Dispatchers.IO).launch {
//                for (i in 1..10) {
//
//                    parkingDao.insertParking(
//                        Parking(
//                            name = "Parking $i",
//                            address = "Address $i",
//                            image = "",
//                            price = 10.0,
//                        )
//                    )
//                    reservationModel.addReservation(
//                        Reservation(
//                            price = 100.0,
//                            parkingId = i,
//                        )
//                    )
//                }
//                try {
//                    reservationModel.getAllReservations()
//                } catch (e: Exception) {
//                    println("Error fetching data: ${e.message}")
//                }
//            }
//        }) {
//            Text(text = "Create 10 reservations")
//        }
//
//        reservationModel.reservations.value.forEach { reservationInfo ->
//            Box(
//                modifier = Modifier
//                    .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
//                    .background(
//                        MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
//                    )
//                    .fillMaxWidth()
//                    .border(
//                        width = 1.dp,
//                        color = Color.Black
//                    )
//                    .padding(16.dp)
//
//            ) {
//                Text(
//                    text = "Reservation n°${reservationInfo.id}"
//                )
//            }
//        }
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                reservationModel.getAllReservations()
//            } catch (e: Exception) {
//                println("Error fetching data: ${e.message}")
//            }
//        }
//    }
}