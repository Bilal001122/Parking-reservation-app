package com.example.parkirapp.ui.screens.parkings_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.parkirapp.R
import com.example.parkirapp.data.models.Parking
import com.example.parkirapp.ui.screens.parkings_list.components.ParkingItem
import com.example.parkirapp.ui.theme.blackColor
import com.example.parkirapp.ui.theme.greyColor

val parkingList = listOf(
    Parking(
        id = 1,
        name = "Parking A",
        exactLocationDetails = "123 Main Street",
        image = R.drawable.parking,
        capacity = 50,
        openAt = "08 AM",
        closingAt = "10 PM",
        description = "This parking lot offers convenient access to nearby attractions.",
        pricePerHour = 5.0,
        distanceFromLocation = 1.2
    ),
    Parking(
        id = 2,
        name = "Parking B",
        exactLocationDetails = "456 Elm Street",
        image = R.drawable.parking,
        capacity = 30,
        openAt = "09 AM",
        closingAt = "09 PM",
        description = "A secure parking option close to shopping centers.",
        pricePerHour = 4.5,
        distanceFromLocation = 0.8
    ),
    Parking(
        id = 3,
        name = "Parking C",
        exactLocationDetails = "789 Oak Street",
        image = R.drawable.parking,
        capacity = 40,
        openAt = "07:30 AM",
        closingAt = "11 PM",
        description = "Convenient parking with easy access to public transportation.",
        pricePerHour = 6.0,
        distanceFromLocation = 1.5
    ),
    Parking(
        id = 4,
        name = "Parking D",
        exactLocationDetails = "101 Maple Street",
        image = R.drawable.parking,
        capacity = 20,
        openAt = "10 AM",
        closingAt = "08:30 PM",
        description = "Ideal for short-term parking needs.",
        pricePerHour = 3.0,
        distanceFromLocation = 0.5
    ),
    Parking(
        id = 5,
        name = "Parking E",
        exactLocationDetails = "222 Pine Street",
        image = R.drawable.parking,
        capacity = 60,
        openAt = "24/7",
        closingAt = "24/7",
        description = "Large parking lot with ample space for vehicles.",
        pricePerHour = 7.0,
        distanceFromLocation = 2.0
    ), Parking(
        id = 6,
        name = "Parking F",
        exactLocationDetails = "333 Cedar Street",
        image = R.drawable.parking,
        capacity = 35,
        openAt = "08:30 AM",
        closingAt = "09:30 PM",
        description = "Affordable parking option with easy access to nearby restaurants.",
        pricePerHour = 4.0,
        distanceFromLocation = 0.9
    ),
    Parking(
        id = 7,
        name = "Parking G",
        exactLocationDetails = "444 Walnut Street",
        image = R.drawable.parking,
        capacity = 25,
        openAt = "09:30 AM",
        closingAt = "10:30 PM",
        description = "Secure parking facility with 24/7 surveillance.",
        pricePerHour = 6.5,
        distanceFromLocation = 1.2
    ),
    Parking(
        id = 8,
        name = "Parking H",
        exactLocationDetails = "555 Birch Street",
        image = R.drawable.parking,
        capacity = 45,
        openAt = "07 AM",
        closingAt = "11 PM",
        description = "Spacious parking lot suitable for events and gatherings.",
        pricePerHour = 5.0,
        distanceFromLocation = 1.8
    ),
    Parking(
        id = 9,
        name = "Parking I",
        exactLocationDetails = "666 Oakwood Street",
        image = R.drawable.parking,
        capacity = 30,
        openAt = "10 AM",
        closingAt = "08 PM",
        description = "Convenient parking option near the city center.",
        pricePerHour = 3.5,
        distanceFromLocation = 0.7
    ),
    Parking(
        id = 10,
        name = "Parking J",
        exactLocationDetails = "777 Maplewood Street",
        image = R.drawable.parking2,
        capacity = 50,
        openAt = "24/7",
        closingAt = "24/7",
        description = "Premium parking service with valet and car wash options.",
        pricePerHour = 8.0,
        distanceFromLocation = 1.5
    )
)

@Composable
fun ParkingListScreen(navController: NavController) {
    LazyColumn() {
        items(
            parkingList
        ) { parking ->
            ParkingItem(parking = parking, navController = navController)
        }
    }
}