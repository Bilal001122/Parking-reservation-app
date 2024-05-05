package com.example.parkirapp.ui.screens.bookings.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.parkirapp.data.api.models.Reservation
import com.example.parkirapp.ui.theme.blackColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingItemOnGoing(booking: Reservation, navController: NavController) {
    val showBottomSheetForCancel = remember {
        mutableStateOf(false)
    }

    val showBottomSheetForBooking = remember {
        mutableStateOf(false)
    }

    val sheetStateForCancelBooking = rememberModalBottomSheetState()
    val sheetForShowingTicket = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()


    if (
        showBottomSheetForBooking.value
    ) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheetForBooking.value = false
            },
            sheetState = sheetForShowingTicket,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp)),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Your Parking Slot is :${booking.placeNumber}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    if (showBottomSheetForCancel.value) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheetForCancel.value = false
            },
            sheetState = sheetStateForCancelBooking,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp)),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Cancel Booking",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = Color.Red.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Are you sure you want to cancel this booking?",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = blackColor,
                    textAlign = TextAlign.Center
                )
            }
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(
                    modifier = Modifier
                        .weight(1f),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                    onClick = {
                        scope.launch { sheetStateForCancelBooking.hide() }.invokeOnCompletion {
                            if (!sheetStateForCancelBooking.isVisible) {
                                showBottomSheetForCancel.value = false
                            }
                        }
                    }) {
                    Text(
                        "Cancel",
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
                    )

                }
                Spacer(modifier = Modifier.width(16.dp))
                TextButton(
                    modifier = Modifier
                        .weight(1f),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White
                    ),
                    onClick = {
                        scope.launch { sheetStateForCancelBooking.hide() }.invokeOnCompletion {
                            if (!sheetStateForCancelBooking.isVisible) {
                                showBottomSheetForCancel.value = false
                            }
                        }
                    }) {
                    Text(
                        "Yes, Continue",
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
                    )
                }
            }
        }
    }

    Column(
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
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(4.4f)
                    .padding(12.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(16.dp)),
            ) {
                Image(
                    painter = painterResource(id = booking.parkingImage), contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(
                            RoundedCornerShape(16.dp)
                        ),
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
                        text = booking.parkingName,
                        fontWeight = FontWeight.SemiBold,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 16.sp,
                        maxLines = 1,
                    )
                }
                Row {
                    Text(
                        text = booking.parkingAddress,
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
                            text = " per hour",
                            fontWeight = FontWeight.Medium,
                            fontSize = 10.sp,
                            color = blackColor.copy(alpha = 0.4f)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .border(
                                shape = CircleShape,
                                color = MaterialTheme.colorScheme.primary,
                                width = 1.dp
                            )
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            modifier = Modifier.offset(x = 0.dp, y = 1.dp),
                            text = booking.status.toString(),
                            fontSize = 10.sp,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(
                onClick = {
                    showBottomSheetForCancel.value = true

                },
                shape = CircleShape,
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 5.dp)
                    .weight(1f),
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
            ) {
                Text(
                    text = "Cancel Booking",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            TextButton(
                onClick = {
                    showBottomSheetForBooking.value = true
                },
                shape = CircleShape,
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 5.dp)
                    .weight(1f),
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = "View Ticket",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}