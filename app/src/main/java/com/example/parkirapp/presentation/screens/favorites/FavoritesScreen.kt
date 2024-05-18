package com.example.parkirapp.presentation.screens.favorites

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.parkirapp.business_logic.vm.ParkingsVM
import com.example.parkirapp.presentation.screens.parkings_list.components.ParkingItem

@Composable
fun FavoritesScreen(navController: NavController, parkingsVM: ParkingsVM) {

    val pref = LocalContext.current.getSharedPreferences("parkir_sp", Context.MODE_PRIVATE)
    LaunchedEffect(Unit) {
        pref.getString("token", null)?.let {
            parkingsVM.getFavoriteParkings(it)
        }
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.tertiary
                )
        ) {
            items(
                parkingsVM.favoriteParkings
            ) { parking ->
                ParkingItem(parking = parking, navController = navController)
            }
        }
    }
}