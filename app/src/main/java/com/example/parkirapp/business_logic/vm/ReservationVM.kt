package com.example.parkirapp.business_logic.vm

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.parkirapp.data.api.repos.ParkingsRepository
import com.example.parkirapp.data.api.repos.ReservationRepository
import com.example.parkirapp.data.database.daos.ReservationWithParking
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReservationVM(
    private val reservationRepository: ReservationRepository,
    private val parkingRepository: ParkingsRepository
) : ViewModel() {
    val isLoading = mutableStateOf(true)
    val allReservationWithoutConnexion = mutableStateListOf<ReservationWithParking>()

    fun getAllReservations(authHeader: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                isLoading.value = true
                if(reservationRepository.countReservations() > 0) {
                    allReservationWithoutConnexion.clear()
                    allReservationWithoutConnexion.addAll(reservationRepository.getReservations())
                    isLoading.value = false
                }
                else {
                    try {
                        val response = reservationRepository.getUserBookings(authHeader)
                        val parkingResponse = parkingRepository.getAllParkings()
                        if (response.isSuccessful && parkingResponse.isSuccessful) {
                            parkingRepository.saveParkings(
                                parkingResponse.body()!!.map {
                                    com.example.parkirapp.data.database.entities.Parking(
                                        it.id,
                                        it.allocatedPlaces,
                                        it.maxCapacity,
                                        it.name,
                                        it.exactLocationDetails,
                                        it.longitude,
                                        it.latitude,
                                        it.pricePerHour,
                                        it.description,
                                        it.openAt,
                                        it.closingAt,
                                        it.image,
                                        it.updatedAt,
                                        it.createdAt,
                                    )
                                }
                            )
                            reservationRepository.saveReservations(
                                response.body()!!.map {
                                    com.example.parkirapp.data.database.entities.Reservation(
                                        it.id,
                                        it.userId,
                                        it.startHour,
                                        it.endHour,
                                        it.status,
                                        it.placeNumber,
                                        it.qrCode,
                                        it.totalPrice,
                                        it.date,
                                        it.parkingId,
                                        it.updatedAt,
                                        it.createdAt
                                    )
                                }
                            )
                            allReservationWithoutConnexion.clear()
                            allReservationWithoutConnexion.addAll(reservationRepository.getReservations())
                            isLoading.value = false
                        }
                    } catch (e: Exception) {
                        isLoading.value = false
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    fun cancelReservation(authHeader: String, bookingId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                isLoading.value = true
                try {
                    val response = reservationRepository.cancelBooking(authHeader, bookingId)
                    if (response.isSuccessful) {
                        reservationRepository.updateReservation(bookingId, "CANCELED")
                        allReservationWithoutConnexion.clear()
                        allReservationWithoutConnexion.addAll(reservationRepository.getReservations())
                        isLoading.value = false
                    }
                } catch (e: Exception) {
                    isLoading.value = false
                    e.printStackTrace()
                }
            }
        }
    }

    fun completeReservation(authHeader: String, bookingId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                isLoading.value = true
                try {
                    val response = reservationRepository.completeBooking(authHeader, bookingId)
                    if (response.isSuccessful) {
                        reservationRepository.updateReservation(bookingId, "COMPLETED")
                        allReservationWithoutConnexion.clear()
                        allReservationWithoutConnexion.addAll(reservationRepository.getReservations())
                        isLoading.value = false
                    }
                } catch (e: Exception) {
                    isLoading.value = false
                    e.printStackTrace()
                }
            }
        }
    }


    fun createReservation(
        authHeader: String,
        parkingId: Int,
        date: String,
        startHour: String,
        endHour: String,
        totalPrice: Double
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                isLoading.value = true
                try {
                    val response = reservationRepository.createBooking(
                        authHeader,
                        parkingId,
                        startHour,
                        endHour,
                        date,
                        totalPrice
                    )
                    if (response.isSuccessful) {
                        val newReservation = response.body()
                        if (newReservation != null) {
                            reservationRepository.saveReservation(
                                com.example.parkirapp.data.database.entities.Reservation(
                                    newReservation.id,
                                    newReservation.userId,
                                    newReservation.startHour,
                                    newReservation.endHour,
                                    newReservation.status,
                                    newReservation.placeNumber,
                                    newReservation.qrCode,
                                    newReservation.totalPrice,
                                    newReservation.date,
                                    newReservation.parkingId,
                                    newReservation.updatedAt,
                                    newReservation.createdAt
                                )
                            )
                        }
                        allReservationWithoutConnexion.clear()
                        allReservationWithoutConnexion.addAll(reservationRepository.getReservations())
                        isLoading.value = false
                    }
                } catch (e: Exception) {
                    isLoading.value = false
                    e.printStackTrace()
                }
            }
        }
    }

    fun deleteAllReservations() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                reservationRepository.deleteAllReservations()
            }
        }
    }

    class Factory(
        private val reservationRepository: ReservationRepository,
        private val parkingRepository: ParkingsRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ReservationVM(reservationRepository,parkingRepository) as T
        }
    }
}