package com.example.parkirapp.ui.screens.reservation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservationScreen(parkingId: Int?, navController: NavHostController) {
//    val parking = parkingList.find { it.id == parkingId }
//
//    val datePickerState = rememberDatePickerState()
//    val selectedDate = datePickerState.selectedDateMillis?.let {
//        convertMillisToDate(it)
//    }
//    val enteredTime = rememberTimePickerState()
//    val showEnteredTime = remember { mutableStateOf(false) }
//    val sortingTime = rememberTimePickerState()
//    val showSortingTime = remember { mutableStateOf(false) }
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(
//                horizontal = 16.dp, vertical = 16.dp
//            )
//            .verticalScroll(rememberScrollState()),
//    ) {
//        Text(
//            text = "Select Date",
//            fontWeight = FontWeight.SemiBold,
//        )
//        DatePicker(
//            state = datePickerState,
//
//            )
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceEvenly,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Column(
//                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxWidth(),
//            ) {
//                Text(text = "Start Hour", fontWeight = FontWeight.SemiBold)
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .clip(
//                            RoundedCornerShape(8.dp)
//                        )
//                        .background(
//                            MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
//                        )
//                        .padding(horizontal = 12.dp, vertical = 8.dp)
//                ) {
//                    Text(text = "${enteredTime.hour}:${enteredTime.minute} AM")
//                    Spacer(modifier = Modifier.width(12.dp))
//                    Icon(
//                        painter = painterResource(id = R.drawable.hour),
//                        contentDescription = null,
//                        modifier = Modifier.clickable {
//                            showEnteredTime.value = true
//                        }
//                    )
//                }
//                if (showEnteredTime.value) {
//                    DatePickerDialog(
//                        onDismissRequest = {
//                            showEnteredTime.value = false
//                        },
//                        confirmButton = {
//                            Button(onClick = {
//                                showEnteredTime.value = false
//                            }) {
//                                Text("OK")
//                            }
//                        },
//                    ) {
//                        TimePicker(state = enteredTime)
//                    }
//                }
//            }
//            Icon(
//                imageVector = Icons.Filled.ArrowForward, contentDescription = null,
//                modifier = Modifier
//                    .padding(
//                        horizontal = 8.dp,
//                    )
//                    .padding(
//                        top = 26.dp,
//                    ),
//            )
//            Column(
//                modifier = Modifier.weight(1f)
//            ) {
//                Text(text = "End Hour", fontWeight = FontWeight.SemiBold)
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .clip(
//                            RoundedCornerShape(8.dp)
//                        )
//                        .background(
//                            MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
//                        )
//                        .padding(horizontal = 12.dp, vertical = 8.dp),
//                ) {
//                    Text(text = "${sortingTime.hour}:${sortingTime.minute} PM")
//                    Spacer(modifier = Modifier.width(12.dp))
//                    Icon(
//                        painter = painterResource(id = R.drawable.hour),
//                        contentDescription = null,
//                        modifier = Modifier.clickable {
//                            showSortingTime.value = true
//                        }
//                    )
//                }
//
//                if (showSortingTime.value) {
//                    DatePickerDialog(
//                        onDismissRequest = {
//                            showSortingTime.value = false
//                        },
//                        confirmButton = {
//                            Button(onClick = {
//                                showSortingTime.value = false
//                            }) {
//                                Text("OK")
//                            }
//                        },
//                    ) {
//                        TimePicker(
//                            state = sortingTime,
//                            colors = TimePickerDefaults.colors(
//                                containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
//                                clockDialColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
//                                timeSelectorUnselectedContainerColor = MaterialTheme.colorScheme.primary.copy(
//                                    alpha = 0.1f
//                                ),
//                                timeSelectorSelectedContentColor = MaterialTheme.colorScheme.primary,
//                            ),
//
//                            )
//                    }
//                }
//            }
//        }
//        Spacer(modifier = Modifier.height(24.dp))
//        Text(text = "Total", fontWeight = FontWeight.SemiBold)
//        Row(
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                text = "$${
//                    parking?.pricePerHour?.times(
//                        calculateHourDifference(
//                            enteredTime.hour,
//                            sortingTime.hour
//                        ).toDouble()
//                    )
//                } ",
//                fontWeight = FontWeight.Bold,
//                fontSize = 26.sp,
//                color = MaterialTheme.colorScheme.primary
//            )
//            Text(
//                text = "/ ${calculateHourDifference(enteredTime.hour, sortingTime.hour)} hours",
//                fontWeight = FontWeight.Medium
//            )
//        }
//        CustomButton(
//            text = "Continue",
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(
//                    top = 16.dp
//                ),
//            padding = 12.dp,
//        ) {
//            navController.navigate(Destination.Payment.route)
//        }
//
//    }
}

//@SuppressLint("SimpleDateFormat")
//private fun convertMillisToDate(millis: Long): String {
//    val formatter = SimpleDateFormat("dd/MM/yyyy")
//    return formatter.format(Date(millis))
//}
//
//fun calculateHourDifference(startHour: Int, endHour: Int): Int {
//    var difference = endHour - startHour
//    if (difference < 0) {
//        difference += 24
//    }
//    return difference
//}