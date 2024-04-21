package com.example.parkirapp.data.repos

import com.example.parkirapp.data.db.Reservation
import com.example.parkirapp.data.db.ReservationDao

class ReservationRepo(
    private val reservationDao: ReservationDao
) {
    fun getAllReservations() = reservationDao.getAllReservations()
    fun getReservationById(id: Int) = reservationDao.getReservationById(id)

    fun insertReservation(reservation: Reservation) = reservationDao.insertReservation(reservation)
    fun deleteReservation(reservation: Reservation) = reservationDao.deleteReservation(reservation)
    fun updateReservation(reservation: Reservation) = reservationDao.updateReservation(reservation)
}