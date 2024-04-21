package com.example.parkirapp

import android.app.Application
import com.example.parkirapp.data.db.AppDatabase
import com.example.parkirapp.data.repos.ReservationRepo

class MyApplication : Application() {

    private val dataBase by lazy {
        AppDatabase.buildDatabase(this)
    }

    private val reservationDao by lazy {
        dataBase.getReservationDao()
    }

    val reservationRepo by lazy {
        ReservationRepo(reservationDao)
    }
}