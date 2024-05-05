package com.example.parkirapp.data.api.repos

import com.example.parkirapp.data.database.daos.ReservationDao
import com.example.parkirapp.data.database.entities.Reservation

class ReservationRepo(
    private val reservationDao: ReservationDao
) {
    fun getAllReservations() = reservationDao.getAllReservations()
    fun getReservationById(id: Int) = reservationDao.getReservationById(id)
    fun insertReservation(reservation: Reservation) = reservationDao.insertReservation(reservation)
    fun deleteReservation(reservation: Reservation) = reservationDao.deleteReservation(reservation)
    fun updateReservation(reservation: Reservation) = reservationDao.updateReservation(reservation)
}