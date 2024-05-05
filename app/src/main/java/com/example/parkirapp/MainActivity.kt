package com.example.parkirapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.parkirapp.data.api.vm.LoginVM
import com.example.parkirapp.data.api.vm.ParkingsVM
import com.example.parkirapp.data.api.vm.RegistrationVM
import com.example.parkirapp.data.api.vm.ReservationVM
import com.example.parkirapp.ui.navigation.Destination
import com.example.parkirapp.ui.navigation.Navigation
import com.example.parkirapp.ui.theme.ParkirAppTheme

class MainActivity : ComponentActivity() {
    private val reservationModel: ReservationVM by lazy {
        ReservationVM((application as MyApplication).reservationRepo)
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
                        reservationModel = reservationModel,
                        registrationVM = registrationVM,
                        loginVM = loginVM,
                        parkingsVM = parkingsVM
                    )
                }
            }
        }
    }
}


