package com.example.parkirapp.data.api.vm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.parkirapp.data.database.entities.Reservation
import com.example.parkirapp.data.api.repos.ReservationRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReservationVM(
    private val reservationRepo: ReservationRepo
) : ViewModel() {
    var reservations = mutableStateOf(listOf<Reservation>())

    fun getAllReservations() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                reservations.value = reservationRepo.getAllReservations()
            }
        }
    }

    fun addReservation(reservation: Reservation) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                reservationRepo.insertReservation(reservation)
                getAllReservations()
            }
        }
    }

    class Factory(
        private val reservationRepo: ReservationRepo
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ReservationVM(reservationRepo) as T
        }
    }
}