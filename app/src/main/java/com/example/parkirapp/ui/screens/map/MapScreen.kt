package com.example.parkirapp.ui.screens.map

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.parkirapp.R
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.MarkerState

//@Composable
//fun MapScreen(navController: NavController) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.LightGray.copy(alpha = 0.15f))
//    ) {
//        ParkingListScreen(navController = navController)
//    }
//}

@Composable
fun MapScreen(navController: NavController) {
  //  val icon = BitmapDescriptorFactory.fromResource(R.drawable.location_pin)
//    val parkingList = listOf(
//        Parking(
//            id = 1,
//            name = "Parking A",
//            exactLocationDetails = "123 Main Street",
//            image = R.drawable.parking2,
//            capacity = 50,
//            openAt = "08 AM",
//            closingAt = "10 PM",
//            description = "This parking lot offers convenient access to nearby attractions.",
//            pricePerHour = 5.0,
//            distanceFromLocation = 1.2 ,
//            lat = 14.8,
//            lng = 12.5
//        ),
//        Parking(
//            id = 2,
//            name = "Parking B",
//            exactLocationDetails = "456 Elm Street",
//            image = R.drawable.parking,
//            capacity = 30,
//            openAt = "09 AM",
//            closingAt = "09 PM",
//            description = "A secure parking option close to shopping centers.",
//            pricePerHour = 4.5,
//            distanceFromLocation = 0.8 ,
//            lat = 14.8,
//            lng = 12.5
//
//        ),
//        Parking(
//            id = 3,
//            name = "Parking C",
//            exactLocationDetails = "789 Oak Street",
//            image = R.drawable.parking2,
//            capacity = 40,
//            openAt = "07:30 AM",
//            closingAt = "11 PM",
//            description = "Convenient parking with easy access to public transportation.",
//            pricePerHour = 6.0,
//            distanceFromLocation = 1.5 ,
//            lat = 14.8,
//            lng = 12.5
//        ),
//
//        )
    val uiSettings = remember { MapUiSettings(zoomControlsEnabled = false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray.copy(alpha = 0.15f))
    ) {

        val singapore = LatLng(1.35, 103.87)

        GoogleMap(
            uiSettings = uiSettings,
            modifier = Modifier.fillMaxSize(),
            onMapLongClick = {}
        ) {
            Marker(
                state = MarkerState(position = singapore),
                onClick = {
                    it.showInfoWindow()
                    true
                }
            )

            MarkerInfoWindowContent(
                state = MarkerState(position = singapore),
            ) { marker ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(35.dp)
                        )
                        .padding(10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.parking),
                            contentDescription = "parking",
                            modifier = Modifier.height(90.dp)
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = "Marker Title",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.headlineSmall,
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                    }
                }
            }
        }
    }
}