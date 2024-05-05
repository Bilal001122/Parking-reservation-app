package com.example.parkirapp.data.api

import com.example.parkirapp.data.api.models.Parking
import com.example.parkirapp.data.api.models.UserData
import com.example.parkirapp.data.api.models.UserLogin
import com.example.parkirapp.data.api.models.UserResponse
import com.example.parkirapp.data.api.models.UserResponseLogin
import com.example.parkirapp.utils.BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Endpoints {
    @POST("api/auth/signUp")
    suspend fun signUpUser(
        @Body userData: UserData,
    ): Response<UserResponse>

    @POST("api/auth/logIn")
    suspend fun logInUser(
        @Body userData: UserLogin
    ): Response<UserResponseLogin>

    @GET("api/parking")
    suspend fun getParkings(): Response<List<Parking>>

    @GET("api/parking/{id}")
    suspend fun getParkingById(@Path("id") id: Int): Response<Parking>

    companion object {
        var endpoint: Endpoints? = null
        fun createEndpoint(): Endpoints {
            if (endpoint == null) {
                endpoint = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build()
                    .create(Endpoints::class.java)
            }
            return endpoint!!
        }
    }
}