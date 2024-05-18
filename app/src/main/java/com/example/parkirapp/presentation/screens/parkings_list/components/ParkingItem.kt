package com.example.parkirapp.presentation.screens.parkings_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.parkirapp.R
import com.example.parkirapp.data.api.models.Parking
import com.example.parkirapp.presentation.navigation.Destination
import com.example.parkirapp.presentation.theme.blackColor
import com.example.parkirapp.utils.BASE_URL

@Composable
fun ParkingItem(parking: Parking, navController: NavController) {

    Row(
        modifier = Modifier
            .padding(
                vertical = 8.dp,
                horizontal = 12.dp
            )
            .shadow(
                elevation = 13.dp,
                spotColor = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(8.dp),
            )
            .clip(RoundedCornerShape(16.dp))
            .background(
                MaterialTheme.colorScheme.tertiary
            )
            .fillMaxHeight()
            .clickable {
                navController.navigate(Destination.ParkingDetails.createRoute(parkingId = parking.id))
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(4.4f)
                .padding(start = 12.dp)
                .height(140.dp)
                .clip(RoundedCornerShape(16.dp)),

            ) {
            AsyncImage(
                model = "${BASE_URL}${parking.image}",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize(),
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .weight(6f)
                .fillMaxHeight()
                .padding(
                    vertical = 12.dp,
                    horizontal = 8.dp
                ),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Text(
                    text = parking.name,
                    fontWeight = FontWeight.SemiBold,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
                    maxLines = 1,
                )
            }
            Row {
                Text(
                    text = parking.exactLocationDetails,
                    fontWeight = FontWeight.Medium,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 14.sp,
                    maxLines = 1,
                    color = blackColor.copy(alpha = 0.6f)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
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

            Column() {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,


                ) {
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                        modifier = Modifier
//                            .border(
//                                shape = CircleShape,
//                                color = MaterialTheme.colorScheme.primary,
//                                width = 1.dp
//                            )
//                            .padding(horizontal = 12.dp, vertical = 2.dp)
//                    ) {
//                        Icon(
//                            Icons.Filled.LocationOn,
//                            contentDescription = null,
//                            tint = MaterialTheme.colorScheme.primary,
//                            modifier = Modifier.size(16.dp)
//                        )
//                        Spacer(modifier = Modifier.width(4.dp))
//                        Text(
//                            modifier = Modifier.offset(x = 0.dp, y = 1.dp),
//                            text = " km",
//                            fontSize = 12.sp,
//                            textAlign = TextAlign.Center,
//                        )
//                    }
//                    Spacer(modifier = Modifier.width(8.dp))
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
                Spacer(modifier = Modifier.height(8.dp))
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
                        painter = painterResource(id = R.drawable.hour),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        modifier = Modifier.offset(x = 0.dp, y = 1.dp),
                        text = "${parking.openAt} - ${parking.closingAt}",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}