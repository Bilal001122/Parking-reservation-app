package com.example.parkirapp.data.api.repos

import com.example.parkirapp.data.api.Endpoints
import com.example.parkirapp.data.api.LoginWithGoogleRequest
import com.example.parkirapp.data.api.RegisterTokenRequest
import com.example.parkirapp.data.api.UserLogin
import com.example.parkirapp.data.api.UserResponse
import com.example.parkirapp.data.api.UserResponseLogin
import retrofit2.Response

class LoginRepository {
    suspend fun logInUser(
        email: String,
        password: String
    ): Response<UserResponseLogin> {
        val userLogin = UserLogin(email, password)
        return Endpoints.createEndpoint().logInUser(userLogin)
    }

    suspend fun getUserInfo(
        authHeader: String
    ): Response<UserResponse> {
        return Endpoints.createEndpoint().getUserInformation(authHeader)
    }

    suspend fun registerToken(
        authHeader: String,
        token: String
    ): Response<String> {
        val registerTokenRequest: RegisterTokenRequest = RegisterTokenRequest(token)
        return Endpoints.createEndpoint().registerToken(authHeader, registerTokenRequest)
    }

    suspend fun loginWithGoogle(
        token: String
    ): Response<UserResponseLogin> {
        val loginWithGoogleRequest = LoginWithGoogleRequest(token)
        return Endpoints.createEndpoint().loginWithGoogle(loginWithGoogleRequest)
    }
}