package com.example.parkirapp.data.api

import com.example.parkirapp.data.api.models.Parking
import com.example.parkirapp.data.api.models.Reservation
import com.example.parkirapp.data.api.models.User
import com.example.parkirapp.utils.BASE_URL
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

data class UserResponse(
    @SerializedName("status") val status: String,
    @SerializedName("user") val user: User
)

data class UserResponseLogin(
    @SerializedName("status") val status: String,
    @SerializedName("user") val user: User,
    @SerializedName("token") val token: String
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

data class CancelBookingRequest(val bookingId: Int)

data class CompleteBookingRequest(val bookingId: Int)

data class CreateBookingRequest(
    val parkingId: Int,
    val booking: BookingRequest,
)

data class BookingRequest(
    val startHour: String,
    val endHour: String,
    val date: String,
    val totalPrice: Double,
    )

data class AddParkingToFavoritesResponse(
    val id: Int,
    @SerializedName("UserId")
    val userId: Int,
    @SerializedName("ParkingId")
    val parkingId: Int,
    @Transient
    val updatedAt : String,
    @Transient
    val createdAt : String
)

data class RemoveParkingFromFavoritesResponse(
    val message : String
)

data class GetFavoriteParkingsResponse(
    val favorites: List<Parking>
)

data class RemoveFavoriteParkingRequest(
    val parkingId: Int
)

data class AddParkingToFavoritesRequest(
    val parkingId: Int
)

data class RegisterTokenRequest(
    val token: String
)

data class LoginWithGoogleRequest(
    val token: String
)
interface Endpoints {

    @POST("api/sendToken")
    suspend fun sendToken(
        @Header("Authorization") authHeader: String,
        @Body token: String
    ): Response<String>

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

    @GET("api/booking")
    suspend fun getUserBookings(@Header("Authorization") authHeader: String): Response<List<Reservation>>

    @POST("api/booking/cancel")
    suspend fun cancelBooking(
        @Header("Authorization") authHeader: String,
        @Body bookingId: CancelBookingRequest
    ): Response<Reservation>

    @POST("api/booking/complete")
    suspend fun completeBooking(
        @Header("Authorization") authHeader: String,
        @Body bookingId: CompleteBookingRequest
    ): Response<Reservation>


    @POST("api/booking/create")
    suspend fun createBooking(
        @Header("Authorization") authHeader: String,
        @Body booking: CreateBookingRequest
    ): Response<Reservation>

    @GET("api/auth/myProfile")
    suspend fun getUserInformation(@Header("Authorization") authHeader: String): Response<UserResponse>

    @POST("api/parking/favorite")
    suspend fun addParkingToFavorites(
        @Header("Authorization") authHeader: String,
        @Body addParkingToFavoritesRequest: AddParkingToFavoritesRequest
    ): Response<AddParkingToFavoritesResponse>

    @POST("api/parking/removeFavorite")
    suspend fun removeParkingFromFavorites(
        @Header("Authorization") authHeader: String,
        @Body removeFavoriteParkingRequest: RemoveFavoriteParkingRequest
    ): Response<RemoveParkingFromFavoritesResponse>

    @GET("api/parking/favorites")
    suspend fun getFavoriteParkings(@Header("Authorization") authHeader: String): Response<GetFavoriteParkingsResponse>

    @POST("api/firebase/registerToken")
    suspend fun registerToken(
        @Header("Authorization") authHeader: String,
        @Body registerTokenRequest: RegisterTokenRequest
    ): Response<String>

    @POST("api/auth/loginWithGoogle")
    suspend fun loginWithGoogle(
        @Body token: LoginWithGoogleRequest
    ): Response<UserResponseLogin>

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