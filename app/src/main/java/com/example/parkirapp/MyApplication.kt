package com.example.parkirapp

import android.app.Application
import com.example.parkirapp.data.api.repos.LoginRepository
import com.example.parkirapp.data.api.repos.ParkingsRepository
import com.example.parkirapp.data.api.repos.RegistrationRepository
import com.example.parkirapp.data.api.repos.ReservationRepository
import com.example.parkirapp.data.database.AppDatabase

class MyApplication : Application() {

    private val dataBase by lazy {
        AppDatabase.buildDatabase(this)
    }

    private val reservationDao by lazy {
        dataBase.getReservationsDao()
    }

    private val parkingDao by lazy {
        dataBase.getParkingDao()
    }

    val reservationRepository by lazy {
        ReservationRepository(reservationDao,parkingDao)
    }

    val registrationRepository by lazy {
        RegistrationRepository()
    }

    val loginRepository by lazy {
        LoginRepository()
    }

    val parkingsRepository by lazy {
        ParkingsRepository(parkingDao)
    }
}