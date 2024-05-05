package com.example.parkirapp.data.api.vm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.parkirapp.data.api.models.User
import com.example.parkirapp.data.api.repos.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class LoginVM(
    private val loginRepository: LoginRepository
) : ViewModel() {
    val isLoading = mutableStateOf<Boolean?>(null)
    var currentUser: User? = null
    val showToast = mutableStateOf(false)
    val toastMessage = mutableStateOf<String?>(null)
    val token = mutableStateOf<String?>(null)
    val isLoggedInWithSuccess = mutableStateOf<Boolean?>(null)
    fun userLogIn(email: String, password: String) {
        viewModelScope.launch {
            isLoading.value = true
            withContext(Dispatchers.IO) {
                try {
                    val response = loginRepository.logInUser(email, password)
                    if (response.isSuccessful) {
                        isLoggedInWithSuccess.value = true
                        isLoading.value = false
                        currentUser = response.body()?.user
                        token.value = response.body()?.token
                        toastMessage.value = "Login successful"
                        showToast.value = true
                    } else {
                        isLoading.value = false
                        isLoggedInWithSuccess.value = false
                        showToast.value = true
                        val jsonObject = response.errorBody()?.string()?.let { JSONObject(it) }
                        toastMessage.value = jsonObject?.getString("message").toString()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    isLoading.value = false
                    toastMessage.value = e.message
                    showToast.value = true
                }
            }
        }
    }

    class Factory(private val loginRepository: LoginRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LoginVM(loginRepository) as T
        }
    }
}