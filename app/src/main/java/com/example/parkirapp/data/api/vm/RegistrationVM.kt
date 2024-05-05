package com.example.parkirapp.data.api.vm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.parkirapp.data.api.repos.RegistrationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class RegistrationVM(private val registrationRepository: RegistrationRepository) : ViewModel() {

    val isLoading = mutableStateOf<Boolean?>(null)
    val registrationSuccess = mutableStateOf<Boolean?>(null)
    val showToast = mutableStateOf(false)
    val toastMessage = mutableStateOf<String?>(null)
    fun signUpUser(
        email: String,
        password: String,
        fullName: String,
        dateOfBirth: String,
        phone: String,
        gender: String
    ) {
        viewModelScope.launch {
            isLoading.value = true
            withContext(Dispatchers.IO) {
                try {
                    val response =
                        registrationRepository.signUpUser(
                            email,
                            password,
                            fullName,
                            dateOfBirth,
                            phone,
                            gender
                        )
                    if (response.isSuccessful) {
                        isLoading.value = false
                        registrationSuccess.value = true
                        toastMessage.value = "Registration successful"
                        showToast.value = true
                    } else {
                        val jsonObject = response.errorBody()?.string()?.let { JSONObject(it) }
                        isLoading.value = false
                        registrationSuccess.value = false
                        toastMessage.value = jsonObject?.getString("message").toString().ifEmpty {
                            "Registration failed"
                        }
                        showToast.value = true
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    isLoading.value = false
                    registrationSuccess.value = false
                    toastMessage.value = e.message
                    showToast.value = true
                }
            }
        }
    }

    class Factory(private val registrationRepository: RegistrationRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RegistrationVM(registrationRepository) as T
        }
    }
}