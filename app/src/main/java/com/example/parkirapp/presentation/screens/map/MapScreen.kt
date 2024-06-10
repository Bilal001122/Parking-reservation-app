package com.example.parkirapp.presentation.screens.map

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.parkirapp.R
import com.example.parkirapp.business_logic.vm.ParkingsVM
import com.example.parkirapp.presentation.navigation.Destination
import com.example.parkirapp.presentation.shared.CustomButton
import com.example.parkirapp.presentation.theme.blackColor
import com.example.parkirapp.utils.BASE_URL
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun MapScreen(navController: NavController, parkingsVM: ParkingsVM) {

    LaunchedEffect(Unit) {
        parkingsVM.getAllParkings()
    }

    val pref = LocalContext.current.getSharedPreferences("parkir_sp", Context.MODE_PRIVATE)

    val uiSettings = remember {
        MapUiSettings(
            zoomControlsEnabled = true,
        )
    }

    if (parkingsVM.isLoading.value) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray.copy(alpha = 0.15f))
        ) {
            GoogleMap(
                uiSettings = uiSettings,
                modifier = Modifier.fillMaxSize(),
                onMapLongClick = {},
                cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(
                        LatLng(36.7590468, 3.0532464), 12f
                    )
                },
            ) {
                for (parking in parkingsVM.allParkings) {
                    Marker(
                        state = MarkerState(position = LatLng(parking.latitude, parking.longitude)),
                        onClick = {
                            it.showInfoWindow()
                            true
                        },
                    )
                    MarkerInfoWindowContent(
                        state = MarkerState(position = LatLng(parking.latitude, parking.longitude)),
                        onInfoWindowClick = {
                            navController.navigate(Destination.ParkingDetails.createRoute(parking.id))
                        }
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .fillMaxWidth(0.7f)
                                .background(
                                    color = Color.White,
                                )
                                .padding(10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp)),
                                horizontalAlignment = Alignment.Start,
                            ) {
                                Text(
                                    text = parking.name,
                                    fontWeight = FontWeight.SemiBold,
                                    overflow = TextOverflow.Ellipsis,
                                    fontSize = 16.sp,
                                    maxLines = 1,
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data("$BASE_URL${parking.image}")
                                        .allowHardware(false)
                                        .build(),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .height(150.dp)
                                        .fillMaxWidth()
                                        .clip(
                                            RoundedCornerShape(10.dp)
                                        ),

                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row {
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
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier
                                            .border(
                                                shape = CircleShape,
                                                color = MaterialTheme.colorScheme.primary,
                                                width = 1.dp
                                            )
                                            .padding(horizontal = 12.dp, vertical = 2.dp)
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.car),
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            modifier = Modifier.offset(x = 0.dp, y = 1.dp),
                                            text = "${parking.allocatedPlaces} / ${parking.maxCapacity}",
                                            fontSize = 12.sp,
                                            textAlign = TextAlign.Center,
                                        )
                                    }

                                }
                                CustomButton(
                                    text = "View Details",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            top = 16.dp
                                        ),
                                    padding = 12.dp,
                                    fontSize = 12.sp
                                ) {
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}