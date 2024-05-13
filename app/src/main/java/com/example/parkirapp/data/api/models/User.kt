package com.example.parkirapp.data.api.models

import com.google.gson.annotations.SerializedName
import java.math.BigInteger


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

