package com.example.parkirapp.data.api.repos

import com.example.parkirapp.data.api.AddParkingToFavoritesRequest
import com.example.parkirapp.data.api.AddParkingToFavoritesResponse
import com.example.parkirapp.data.api.Endpoints
import com.example.parkirapp.data.api.GetFavoriteParkingsResponse
import com.example.parkirapp.data.api.RemoveFavoriteParkingRequest
import com.example.parkirapp.data.api.RemoveParkingFromFavoritesResponse
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

    suspend fun addParkingToFavorites(
        authHeader: String,
        parkingId: Int
    ): Response<AddParkingToFavoritesResponse> {
        val request = AddParkingToFavoritesRequest(parkingId)
        return Endpoints.createEndpoint().addParkingToFavorites(authHeader, request)
    }

    suspend fun removeParkingFromFavorites(
        authHeader: String,
        parkingId: Int
    ): Response<RemoveParkingFromFavoritesResponse> {
        val request = RemoveFavoriteParkingRequest(parkingId)
        return Endpoints.createEndpoint().removeParkingFromFavorites(authHeader, request)
    }

    suspend fun getFavoriteParkings(
        authHeader: String
    ): Response<GetFavoriteParkingsResponse> {
        return Endpoints.createEndpoint().getFavoriteParkings(authHeader)
    }
}