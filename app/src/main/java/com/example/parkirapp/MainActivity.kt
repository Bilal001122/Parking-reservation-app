package com.example.parkirapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.parkirapp.data.vm.ReservationModel
import com.example.parkirapp.ui.navigation.Destination
import com.example.parkirapp.ui.navigation.Navigation
import com.example.parkirapp.ui.theme.ParkirAppTheme

class MainActivity : ComponentActivity() {
    private val reservationModel: ReservationModel by lazy {
        ReservationModel((application as MyApplication).reservationRepo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ParkirAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val pref = this.getSharedPreferences("parkir_sp", Context.MODE_PRIVATE)
                    var isLoggedIn: Boolean = pref.getBoolean("isLoggedIn", false)
                    Navigation(
                        navController = rememberNavController(),
                        startDestination = if (isLoggedIn) Destination.Layout else Destination.Login,
                        reservationModel = reservationModel
                    )
                }
            }
        }
    }
}


