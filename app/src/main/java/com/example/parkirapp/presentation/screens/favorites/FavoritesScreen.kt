package com.example.parkirapp.presentation.screens.favorites

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.parkirapp.business_logic.vm.ParkingsVM
import com.example.parkirapp.presentation.screens.parkings_list.components.ParkingItem

@Composable
fun FavoritesScreen(
    navController: NavController,
    parkingsVM: ParkingsVM,
) {

    val pref = LocalContext.current.getSharedPreferences("parkir_sp", Context.MODE_PRIVATE)
//    val filteredParkings = remember {
//        mutableStateListOf<Parking>()
//    }

    val searchField = remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {
        pref.getString("token", null)?.let {
            parkingsVM.getFavoriteParkings(it)
        }
    }

    if (parkingsVM.isFavoriteLoading.value) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column {
            OutlinedTextField(
                value = searchField.value,
                maxLines = 1,
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Black,
                ),
                shape = RoundedCornerShape(
                    size = 10.dp,
                ),
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = null)
                },
                colors = TextFieldDefaults.colors(
                    disabledContainerColor = MaterialTheme.colorScheme.tertiary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.tertiary,
                    focusedContainerColor = MaterialTheme.colorScheme.primary.copy(0.1f),
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.primary.copy(0.7f),
                    cursorColor = MaterialTheme.colorScheme.primary,
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                onValueChange = { newValue: String ->
                    searchField.value = newValue
                    parkingsVM.filteredParkings.clear()
                    parkingsVM.filteredParkings.addAll(
                        if (newValue.isEmpty()) {
                            parkingsVM.favoriteParkings
                        } else {
                            parkingsVM.favoriteParkings.filter {
                                it.name.contains(newValue, ignoreCase = true)
                            }
                        }
                    )
                },
                placeholder = {
                    Text(text = "Search", fontSize = 14.sp, textAlign = TextAlign.Center)
                },
                modifier = Modifier
                    .shadow(
                        elevation = 13.dp,
                        spotColor = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(8.dp),
                    )
                    .background(
                        MaterialTheme.colorScheme.tertiary
                    )
                    .padding(
                        start = 12.dp,
                        top = 16.dp,
                        end = 12.dp,
                        bottom = 4.dp
                    )
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(8.dp)
                    )
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        MaterialTheme.colorScheme.tertiary
                    )
            ) {
                items(
                    parkingsVM.filteredParkings
                ) { parking ->
                    ParkingItem(
                        parking = parking,
                        navController = navController,
                    )
                }
            }
        }
    }
}