package com.example.parkirapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.parkirapp.data.database.daos.ParkingDao
import com.example.parkirapp.data.database.daos.ReservationDao
import com.example.parkirapp.data.database.entities.Parking
import com.example.parkirapp.data.database.entities.Reservation

@Database(entities = [Reservation::class, Parking::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getParkingDao(): ParkingDao
    abstract fun getReservationDao(): ReservationDao
    companion object {
        private var INSTANCE: AppDatabase? = null
        fun buildDatabase(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance =
                        Room.databaseBuilder(
                            context, AppDatabase::class.java,
                            "parkir_db"
                        ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}