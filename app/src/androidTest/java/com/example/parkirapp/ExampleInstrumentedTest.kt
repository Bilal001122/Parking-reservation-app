package com.example.parkirapp

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.parkirapp.data.db.AppDatabase
import com.example.parkirapp.data.db.Parking
import com.example.parkirapp.data.db.Reservation
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.parkirapp", appContext.packageName)
    }


    private lateinit var mDataBase: AppDatabase

    @Before
    fun initDB() {
        val appContext =
            InstrumentationRegistry.getInstrumentation().targetContext
        mDataBase = Room.inMemoryDatabaseBuilder(
            appContext, AppDatabase::class.java
        ).build()
    }

    @Test
    fun testInsertAndGetReservation() {
        val parking = Parking(
            id = 1,
            name = "parking1",
            address = "address1",
            price = 20.0,
            image = "image1"
        )
        mDataBase.getParkingDao().insertParking(parking)
       val reservation1 = Reservation(
            1,
            20.0,
            1
        )
        mDataBase.getReservationDao().insertReservation(reservation1)
        val reservationFromDb = mDataBase.getReservationDao().getReservationById(reservation1.id)
        assertEquals(reservation1, reservationFromDb)
    }

    @After
    fun closeDb() {
        mDataBase.close()
    }
}