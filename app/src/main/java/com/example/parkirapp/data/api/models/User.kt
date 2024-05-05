package com.example.parkirapp.data.api.models

import com.google.gson.annotations.SerializedName
import java.math.BigInteger

data class UserResponse(
    @SerializedName("status") val status: String,
    @SerializedName("user") val user: User
)

data class UserResponseLogin(
    @SerializedName("status") val status: String,
    @SerializedName("user") val user: User,
    @SerializedName("token") val token: String
)
data class User (
    @SerializedName("id") val id: BigInteger,
    @SerializedName("email") val email: String,
    @Transient val password: String?,
    @SerializedName("fullName") val fullName: String,
    @SerializedName("dateOfBirth") val dateOfBirth: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("gender") val gender: String,
    @Transient val updatedAt: String?,
    @Transient val createdAt: String?
)

data class UserData(
    val email: String,
    val password: String,
    val fullName: String,
    val dateOfBirth: String,
    val phone: String,
    val gender: String
)

data class UserLogin(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)