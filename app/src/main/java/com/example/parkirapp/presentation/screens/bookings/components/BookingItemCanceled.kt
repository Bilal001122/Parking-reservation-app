package com.example.parkirapp.presentation.screens.bookings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.parkirapp.R
import com.example.parkirapp.data.api.models.Reservation
import com.example.parkirapp.presentation.screens.parking_details.components.ParkingField
import com.example.parkirapp.presentation.theme.blackColor
import com.example.parkirapp.utils.BASE_URL

@Composable
fun BookingItemCanceled(booking: Reservation, navController: NavController) {
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
            .fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .weight(5.5f)
                .padding(12.dp)
                .fillMaxHeight()
                .clip(RoundedCornerShape(16.dp)),
        ) {
            AsyncImage(
                model = "$BASE_URL${booking.parking.image}",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .clip(
                        RoundedCornerShape(16.dp)
                    ),
            )
        }

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
                    text = booking.parking.name,
                    fontWeight = FontWeight.SemiBold,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
                    maxLines = 1,
                )
            }
            Row {
                Text(
                    text = booking.parking.exactLocationDetails,
                    fontWeight = FontWeight.Medium,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 14.sp,
                    maxLines = 1,
                    color = blackColor.copy(alpha = 0.6f)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$" + booking.totalPrice.toString(),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = " Total",
                        fontWeight = FontWeight.Medium,
                        fontSize = 10.sp,
                        color = blackColor.copy(alpha = 0.4f)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            ParkingField(
                text = booking.date,
                icon = Icons.Filled.DateRange,
            )
            Spacer(modifier = Modifier.height(8.dp))
            ParkingField(
                text = "${booking.startHour} - ${booking.endHour}",
                icon2 = R.drawable.hour,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .border(
                        shape = CircleShape,
                        color = Color.Red,
                        width = 1.dp
                    )
                    .padding(horizontal = 12.dp, vertical = 2.dp)
            ) {
                Text(
                    modifier = Modifier.offset(x = 0.dp, y = 1.dp),
                    text = booking.status.toString(),
                    fontSize = 10.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Red
                )
            }
        }
    }
}