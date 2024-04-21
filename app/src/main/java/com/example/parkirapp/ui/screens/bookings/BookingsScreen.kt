package com.example.parkirapp.ui.screens.bookings

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.parkirapp.data.db.AppDatabase
import com.example.parkirapp.data.db.Parking
import com.example.parkirapp.data.db.Reservation
import com.example.parkirapp.data.vm.ReservationModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun BookingsScreen(navController: NavController,reservationModel: ReservationModel) {
    val context = LocalContext.current
    val database = AppDatabase.buildDatabase(context)
    val parkingDao = database.getParkingDao()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray.copy(alpha = 0.15f))
            .verticalScroll(rememberScrollState())
    ) {
        TextButton(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                for (i in 1..10) {

                    parkingDao.insertParking(
                        Parking(
                            name = "Parking $i",
                            address = "Address $i",
                            image = "",
                            price = 10.0,
                        )
                    )
                    reservationModel.addReservation(
                        Reservation(
                            price = 100.0,
                            parkingId = i,
                        )
                    )
                }
                try {
                    reservationModel.getAllReservations()
                } catch (e: Exception) {
                    println("Error fetching data: ${e.message}")
                }
            }
        }) {
            Text(text = "Create 10 reservations")
        }

        reservationModel.reservations.value.forEach { reservationInfo ->
            Box(
                modifier = Modifier
                    .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
                    .background(
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                    )
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color.Black
                    )
                    .padding(16.dp)

            ) {
                Text(
                    text = "Reservation n°${reservationInfo.id}"
                )
            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            try {
                reservationModel.getAllReservations()
            } catch (e: Exception) {
                println("Error fetching data: ${e.message}")
            }
        }
    }
}