package com.example.parkirapp.presentation.screens.reservation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.parkirapp.R
import com.example.parkirapp.business_logic.vm.ParkingsVM
import com.example.parkirapp.data.api.models.Parking
import com.example.parkirapp.presentation.navigation.Destination
import com.example.parkirapp.presentation.shared.CustomButton
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservationScreen(parkingId: Int?, navController: NavHostController, parkingsVM: ParkingsVM) {
    val parking: Parking = parkingsVM.allParkings.filter {
        it.id == parkingId
    }.first()

    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    }
    val enteredTime = rememberTimePickerState()
    val showEnteredTime = remember { mutableStateOf(false) }
    val sortingTime = rememberTimePickerState()
    val showSortingTime = remember { mutableStateOf(false) }
    if (parkingsVM.isLoading.value) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 16.dp, vertical = 16.dp
                )
                .verticalScroll(rememberScrollState()),
        ) {
            Text(
                text = "Select Date",
                fontWeight = FontWeight.SemiBold,
            )
            DatePicker(
                state = datePickerState,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                ) {
                    Text(text = "Start Hour", fontWeight = FontWeight.SemiBold)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(
                                RoundedCornerShape(8.dp)
                            )
                            .background(
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                            )
                            .clickable {
                                showEnteredTime.value = true
                            }
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Text(text = "${enteredTime.hour}:${enteredTime.minute} AM")
                        Spacer(modifier = Modifier.width(12.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.hour),
                            contentDescription = null,
                        )
                    }
                    if (showEnteredTime.value) {
                        DatePickerDialog(
                            onDismissRequest = {
                                showEnteredTime.value = false
                            },
                            confirmButton = {
                                Button(onClick = {
                                    showEnteredTime.value = false
                                }) {
                                    Text("OK")
                                }
                            },
                        ) {
                            TimePicker(state = enteredTime)
                        }
                    }
                }
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null,
                    modifier = Modifier
                        .padding(
                            horizontal = 30.dp,
                        )
                        .padding(
                            top = 26.dp,
                        ),
                )
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "End Hour", fontWeight = FontWeight.SemiBold)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(
                                RoundedCornerShape(8.dp)
                            )
                            .background(
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                            )
                            .clickable {
                                showSortingTime.value = true
                            }
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                        ,
                    ) {
                        Text(text = "${sortingTime.hour}:${sortingTime.minute} PM")
                        Spacer(modifier = Modifier.width(12.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.hour),
                            contentDescription = null,
                        )
                    }

                    if (showSortingTime.value) {
                        DatePickerDialog(
                            onDismissRequest = {
                                showSortingTime.value = false
                            },
                            confirmButton = {
                                Button(onClick = {
                                    showSortingTime.value = false
                                }) {
                                    Text("OK")
                                }
                            },
                        ) {
                            TimePicker(
                                state = sortingTime,
                                colors = TimePickerDefaults.colors(
                                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                                    clockDialColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                                    timeSelectorUnselectedContainerColor = MaterialTheme.colorScheme.primary.copy(
                                        alpha = 0.1f
                                    ),
                                    timeSelectorSelectedContentColor = MaterialTheme.colorScheme.primary,
                                ),

                                )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Total", fontWeight = FontWeight.SemiBold)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$${
                        parking.pricePerHour.times(
                            calculateHourDifference(
                                enteredTime.hour,
                                sortingTime.hour
                            ).toDouble()
                        )
                    } ",
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "/ ${calculateHourDifference(enteredTime.hour, sortingTime.hour)} hours",
                    fontWeight = FontWeight.Medium
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
                if (
                    selectedDate != null
                ) {
                    navController.navigate(
                        Destination.Payment.createRoute(
                            selectedDate,
                            "${enteredTime.hour}:${enteredTime.minute}",
                            "${sortingTime.hour}:${sortingTime.minute}",
                            parking.pricePerHour.times(
                                calculateHourDifference(
                                    enteredTime.hour,
                                    sortingTime.hour
                                ).toDouble()
                            ),
                            parking.id
                        )
                    )
                }

            }
        }
    }

}

@SuppressLint("SimpleDateFormat")
private fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd-MM-yyyy")
    return formatter.format(Date(millis))
}

fun calculateHourDifference(startHour: Int, endHour: Int): Int {
    var difference = endHour - startHour
    if (difference < 0) {
        difference += 24
    }
    return difference
}