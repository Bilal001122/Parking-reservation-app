package com.example.parkirapp.ui.screens.parking_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.parkirapp.R
import com.example.parkirapp.ui.navigation.Destination
import com.example.parkirapp.ui.screens.parking_details.components.ParkingField
import com.example.parkirapp.ui.screens.parkings_list.parkingList
import com.example.parkirapp.ui.shared.CustomButton
import com.example.parkirapp.ui.theme.blackColor


@Composable
fun ParkingDetailsScreen(parkingId: Int?, navController: NavHostController) {

    val parking = parkingList.find { it.id == parkingId }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 16.dp, vertical = 16.dp
            ),
    ) {
        if (parking != null) {
            Image(
                painter = painterResource(id = parking.image), contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(3f)
                    .clip(RoundedCornerShape(16.dp)),
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(8f),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            10.dp
                        ),

                    ) {
                    Column {
                        Text(
                            text = parking.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,

                            )
                        Text(text = parking.exactLocationDetails)
                    }
                    IconButton(
                        onClick = {},

                        ) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            10.dp
                        ),
                ) {
                    ParkingField(
                        text = parking.distanceFromLocation.toInt().toString() + " km",
                        icon = Icons.Filled.LocationOn
                    )
                    ParkingField(
                        text = "${parking.openAt} - ${parking.closingAt}",
                        icon2 = R.drawable.hour,
                    )
                    ParkingField(
                        text = parking.capacity.toString(),
                        icon2 = R.drawable.car
                    )

                }

                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .padding(
                            horizontal = 8.dp, vertical = 8.dp
                        )
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Description",
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = parking.description,
                        fontSize = 16.sp,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(
                                RoundedCornerShape(6.dp)
                            )
                            .background(
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                            )
                            .padding(
                                vertical = 2.dp
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceAround,
                    ) {
                        Text(
                            text = "$" + parking.pricePerHour.toString(),
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = " per hour",
                            fontWeight = FontWeight.Medium,
                            fontSize = 10.sp,
                            color = blackColor.copy(alpha = 0.4f)
                        )
                    }
                    CustomButton(
                        text = "Continue",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 16.dp
                            ),
                        padding = 12.dp,
                    ) {
                        navController.navigate(Destination.Reservation.createRoute(parking.id))
                    }
                }
            }
        }
    }
}
