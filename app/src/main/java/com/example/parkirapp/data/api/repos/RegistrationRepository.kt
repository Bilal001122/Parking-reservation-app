package com.example.parkirapp.data.api.repos

import com.example.parkirapp.data.api.Endpoints
import com.example.parkirapp.data.api.UserData
import com.example.parkirapp.data.api.UserResponse
import retrofit2.Response

class RegistrationRepository {
    suspend fun signUpUser(
        email: String,
        password: String,
        fullName: String,
        dateOfBirth: String,
        phone: String,
        gender: String
    ): Response<UserResponse> {
        val userData = UserData(email, password, fullName, dateOfBirth, phone, gender)
        return Endpoints.createEndpoint().signUpUser(userData)
    }
}