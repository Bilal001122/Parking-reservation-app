package com.example.parkirapp.data.api.repos

import com.example.parkirapp.data.api.Endpoints
import com.example.parkirapp.data.api.models.UserLogin
import com.example.parkirapp.data.api.models.UserResponseLogin
import retrofit2.Response

class LoginRepository {
    suspend fun logInUser(
        email: String,
        password: String
    ): Response<UserResponseLogin> {
        val userLogin = UserLogin(email, password)
        return Endpoints.createEndpoint().logInUser(userLogin)
    }
}