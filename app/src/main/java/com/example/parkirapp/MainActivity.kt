package com.example.parkirapp

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.example.parkirapp.business_logic.vm.LoginVM
import com.example.parkirapp.business_logic.vm.ParkingsVM
import com.example.parkirapp.business_logic.vm.RegistrationVM
import com.example.parkirapp.business_logic.vm.ReservationVM
import com.example.parkirapp.presentation.navigation.Destination
import com.example.parkirapp.presentation.navigation.Navigation
import com.example.parkirapp.presentation.theme.ParkirAppTheme

class MainActivity : ComponentActivity() {
    private val reservationVM: ReservationVM by lazy {
        ReservationVM((application as MyApplication).reservationRepository)
    }

    private val registrationVM: RegistrationVM by viewModels {
        RegistrationVM.Factory((application as MyApplication).registrationRepository)
    }

    private val loginVM: LoginVM by viewModels {
        LoginVM.Factory((application as MyApplication).loginRepository)
    }

    private val parkingsVM: ParkingsVM by viewModels {
        ParkingsVM.Factory((application as MyApplication).parkingsRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        requestNotificationsPermissions()
        super.onCreate(savedInstanceState)
        setContent {
            ParkirAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val pref = this.getSharedPreferences("parkir_sp", Context.MODE_PRIVATE)
                    val isLoggedIn: Boolean = pref.getBoolean("isLoggedIn", false)
                        Navigation(
                            navController = rememberNavController(),
                            startDestination = if (isLoggedIn) Destination.Layout else Destination.OnBoarding,
                            reservationVM = reservationVM,
                            registrationVM = registrationVM,
                            loginVM = loginVM,
                            parkingsVM = parkingsVM,
                        )
                }
            }
        }
    }

    private fun requestNotificationsPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val hasPermission = ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
            if (!hasPermission) {
                requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 0)
            }
        }
    }
}


