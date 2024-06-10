package com.example.parkirapp.business_logic.vm

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.parkirapp.data.api.models.Parking
import com.example.parkirapp.data.api.repos.ParkingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ParkingsVM(
    private val parkingsRepository: ParkingsRepository
) : ViewModel() {
    val allParkings = mutableStateListOf<Parking>()
    val favoriteParkings = mutableStateListOf<Parking>()
    val isLoading = mutableStateOf(false)
    val isFavoriteLoading = mutableStateOf(false)
    val filteredParkings = mutableStateListOf<Parking>()
    val parking = mutableStateOf<Parking?>(null)

    fun getAllParkings() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                isLoading.value = true
                try {
                    val response = parkingsRepository.getAllParkings()
                    if (response.isSuccessful) {
                        isLoading.value = false
                        allParkings.clear()
                        allParkings.addAll(response.body()!!)
                    }
                } catch (e: Exception) {
                    isLoading.value = false
                    e.printStackTrace()
                }
            }
        }
    }

    fun getParkingById(parkingId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                isLoading.value = true
                try {
                    val response = parkingsRepository.getParkingById(parkingId)
                    if (response.isSuccessful) {
                        isLoading.value = false
                        parking.value = response.body()
                    }
                } catch (e: Exception) {
                    isLoading.value = false
                    e.printStackTrace()
                }
            }
        }
    }

    fun getFavoriteParkings(authHeader: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                isFavoriteLoading.value = true
                try {
                    val response = parkingsRepository.getFavoriteParkings(authHeader)
                    if (response.isSuccessful) {
                        favoriteParkings.clear()
                        filteredParkings.clear()
                        filteredParkings.addAll(response.body()!!.favorites)
                        favoriteParkings.addAll(response.body()!!.favorites)
                        isFavoriteLoading.value = false
                    }
                } catch (e: Exception) {
                    isFavoriteLoading.value = false
                    e.printStackTrace()
                }
            }
        }
    }
    fun addParkingToFavorites(authHeader: String, parkingId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val response = parkingsRepository.addParkingToFavorites(authHeader, parkingId)
                    if (response.isSuccessful) {
                        val response = parkingsRepository.getFavoriteParkings(authHeader)
                        if (response.isSuccessful) {
                            favoriteParkings.clear()
                            favoriteParkings.addAll(response.body()!!.favorites)
                        }
                    }
                } catch (e: Exception) {
                    isLoading.value = false
                    e.printStackTrace()
                }
            }
        }
    }

    fun removeParkingFromFavorites(authHeader: String, parkingId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val response = parkingsRepository.removeParkingFromFavorites(authHeader, parkingId)
                    if (response.isSuccessful) {
                        val response = parkingsRepository.getFavoriteParkings(authHeader)
                        if (response.isSuccessful) {
                            favoriteParkings.clear()
                            favoriteParkings.addAll(response.body()!!.favorites)
                        }
                    }
                } catch (e: Exception) {
                    isLoading.value = false
                    e.printStackTrace()
                }
            }
        }
    }

    fun removeParkingsFromDatabase() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                parkingsRepository.deleteAllParkings()
            }
        }
    }

    class Factory(private val parkingsRepository: ParkingsRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ParkingsVM(parkingsRepository) as T
        }
    }
}