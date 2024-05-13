package com.example.parkirapp.business_logic.vm

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
    val allParkings = mutableListOf<Parking>()
    val isLoading = mutableStateOf(false)
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

    class Factory(private val parkingsRepository: ParkingsRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ParkingsVM(parkingsRepository) as T
        }
    }
}