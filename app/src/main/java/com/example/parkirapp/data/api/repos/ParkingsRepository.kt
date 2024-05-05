package com.example.parkirapp.data.api.repos

import com.example.parkirapp.data.api.Endpoints
import com.example.parkirapp.data.api.models.Parking
import retrofit2.Response

class ParkingsRepository {
    suspend fun getAllParkings(
    ): Response<List<Parking>> {
        return Endpoints.createEndpoint().getParkings()
    }

    suspend fun getParkingById(
        parkingId: Int
    ): Response<Parking> {
        return Endpoints.createEndpoint().getParkingById(parkingId)
    }
}