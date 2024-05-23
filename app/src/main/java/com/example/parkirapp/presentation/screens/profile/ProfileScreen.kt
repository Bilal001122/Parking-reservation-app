package com.example.parkirapp.presentation.screens.profile

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.parkirapp.business_logic.vm.LoginVM
import com.example.parkirapp.presentation.navigation.Destination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun ProfileScreen(navController: NavController, loginVM: LoginVM) {
    val pref = LocalContext.current.getSharedPreferences("parkir_sp", Context.MODE_PRIVATE)

    LaunchedEffect(Unit) {
        pref.getString("token", null)?.let {
            loginVM.getUserInformation(it)
        }
    }
    if (loginVM.isLoading.value == true) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    } else {
        if (loginVM.currentUser != null) {
            Column(
                modifier = Modifier
                    .padding(
                        vertical = 8.dp,
                        horizontal = 12.dp
                    )
                    .fillMaxSize()
                    .background(
                        MaterialTheme.colorScheme.tertiary
                    ),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Personal Information",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary,
                )
                Column() {
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(
                                RoundedCornerShape(10.dp)
                            )
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
                            .padding(vertical = 10.dp, horizontal = 15.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Full Name: ",
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(text = loginVM.currentUser?.fullName ?: "")
                    }

                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(
                                RoundedCornerShape(10.dp)
                            )
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
                            .padding(vertical = 10.dp, horizontal = 15.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Date of birth: ",
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(text = loginVM.currentUser?.dateOfBirth ?: "")
                    }

                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(
                                RoundedCornerShape(10.dp)
                            )
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
                            .padding(vertical = 10.dp, horizontal = 15.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Gender: ",
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(text = loginVM.currentUser?.gender ?: "")
                    }

                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(
                                RoundedCornerShape(10.dp)
                            )
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
                            .padding(vertical = 10.dp, horizontal = 15.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Email: ",
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(text = loginVM.currentUser?.email ?: "")
                    }

                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(
                                RoundedCornerShape(10.dp)
                            )
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
                            .padding(vertical = 10.dp, horizontal = 15.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Phone: ",
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(text = loginVM.currentUser?.phone ?: "")
                    }
                }
                Column {
                    TextButton(
                        onClick = {
                            navController.navigate(Destination.Favorites.route)
                        },
                        shape = CircleShape,
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                            .fillMaxWidth(),
                        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = MaterialTheme.colorScheme.primary
                        ),
                        contentPadding = PaddingValues(
                            vertical = 12.dp
                        )
                    ) {
                        Text(
                            text = "My Favorites",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorites",
                            tint = Color.White
                        )
                    }
                    TextButton(
                        onClick = {
                            CoroutineScope(Dispatchers.IO).launch {
                                pref.edit().remove("token").apply()
                                pref.edit().remove("isLoggedIn").apply()
                                pref.edit().remove("userId").apply()
                                withContext(Dispatchers.Main) {
                                    navController.navigate(Destination.Login.route)
                                }
                            }
                        },
                        shape = CircleShape,
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                            .fillMaxWidth(),
                        border = BorderStroke(2.dp, Color.Red.copy(alpha = 0.8f)),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.Red.copy(alpha = 0.2f),
                            containerColor = Color.Red.copy(alpha = 0.8f)
                        ),
                        contentPadding = PaddingValues(
                            vertical = 12.dp
                        )
                    ) {
                        Text(
                            text = "Logout",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}