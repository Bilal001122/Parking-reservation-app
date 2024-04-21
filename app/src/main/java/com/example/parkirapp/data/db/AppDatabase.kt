package com.example.parkirapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Reservation::class,Parking::class], version = 1)
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