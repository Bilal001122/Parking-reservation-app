package com.example.parkirapp.presentation.screens.login

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.navigation.NavController
import com.example.parkirapp.R
import com.example.parkirapp.business_logic.vm.LoginVM
import com.example.parkirapp.presentation.navigation.Destination
import com.example.parkirapp.presentation.shared.CustomButton
import com.example.parkirapp.presentation.theme.blackColor
import com.example.parkirapp.presentation.theme.whiteColor
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.messaging.messaging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LoginScreen(navController: NavController, loginVM: LoginVM) {

    val emailFieldValue = remember {
        mutableStateOf("")
    }

    val passwordFieldValue = remember {
        mutableStateOf("")
    }

    val checkBoxState = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    val pref = context.getSharedPreferences("parkir_sp", Context.MODE_PRIVATE)
    val signInRequestCode = 1
    val authResultLauncher =
        rememberLauncherForActivityResult(contract = AuthResultContract()) {
            try {
                val account = it?.getResult(ApiException::class.java)
                Log.v("Google Sign In Bilal", account.toString())
                if (account == null) {
                    Toast.makeText(context, "Google Sign In failed", Toast.LENGTH_SHORT).show()
                } else {
                    CoroutineScope(Dispatchers.IO).launch {
                        // TODO: Handle Google Sign In register token tto backend
                        //onGoogleSignInCompleted(account.idToken!!)
                    }
                    Toast.makeText(context, "Google Sign In success", Toast.LENGTH_SHORT).show()
                }
            } catch (e: ApiException) {
                Log.e("Google Sign In", "Google sign in failed", e)
            }
        }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)

    ) {
        Column(
            modifier = Modifier
                .weight(3f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,


            ) {
            Text(
                text = "Login to your\nAccount",
                fontWeight = FontWeight.SemiBold,
                fontSize = 36.sp,
                lineHeight = 40.sp,
            )
        }
        Column(
            modifier = Modifier.weight(2.2f),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(value = emailFieldValue.value, maxLines = 1, textStyle = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = Color.Black,
            ), shape = RoundedCornerShape(
                size = 10.dp,
            ), colors = TextFieldDefaults.colors(
                disabledContainerColor = blackColor.copy(alpha = 0.04f),
                unfocusedContainerColor = blackColor.copy(alpha = 0.04f),
                focusedContainerColor = MaterialTheme.colorScheme.primary.copy(0.1f),
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.primary,
            ), leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.email), contentDescription = null
                )
            }, onValueChange = { newValue: String ->
                emailFieldValue.value = newValue
            }, placeholder = {
                Text(text = "Email", fontSize = 14.sp, textAlign = TextAlign.Center)
            }, modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = passwordFieldValue.value, maxLines = 1, textStyle = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = Color.Black,
            ), shape = RoundedCornerShape(
                size = 10.dp,
            ), colors = TextFieldDefaults.colors(
                disabledContainerColor = blackColor.copy(alpha = 0.04f),
                unfocusedContainerColor = blackColor.copy(alpha = 0.04f),
                focusedContainerColor = MaterialTheme.colorScheme.primary.copy(0.1f),
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.primary,
            ), leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.lock), contentDescription = null)
            }, onValueChange = { newValue: String ->
                passwordFieldValue.value = newValue
            }, placeholder = {
                Text(text = "Password", fontSize = 14.sp, textAlign = TextAlign.Center)
            }, modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {

                Checkbox(
                    checked = checkBoxState.value, onCheckedChange = { newValue ->
                        checkBoxState.value = newValue
                    }, colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colorScheme.primary,
                        checkmarkColor = whiteColor,
                        uncheckedColor = MaterialTheme.colorScheme.primary,
                    ), modifier = Modifier.clip(RoundedCornerShape(20.dp))
                )
                Text(text = "Remember me", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Column(
            modifier = Modifier.weight(4f),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomButton(
                text = "Sign in",
                modifier = Modifier.fillMaxWidth(),
                padding = 12.dp,
                fontWeight = FontWeight.Medium
            ) {
                when {
                    emailFieldValue.value.isEmpty() -> {
                        Toast.makeText(context, "Email is required", Toast.LENGTH_SHORT).show()
                    }

                    passwordFieldValue.value.isEmpty() -> {
                        Toast.makeText(context, "Password is required", Toast.LENGTH_SHORT).show()
                    }

                    else -> {
                        loginVM.userLogIn(emailFieldValue.value, passwordFieldValue.value)
                    }
                }
            }

            if (loginVM.isLoading.value == true) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }


            if (loginVM.isLoggedInWithSuccess.value == true) {
                navController.popBackStack(Destination.Login.route, inclusive = true)
                navController.navigate(Destination.Layout.route) {
                    launchSingleTop = true
                    restoreState = true
                }

                CoroutineScope(Dispatchers.IO).launch {
                    pref.edit {
                        putBoolean("isLoggedIn", true)
                        putString("token", loginVM.token.value)
                        putString("userId", loginVM.currentUser?.id.toString())
                    }
                    val storedFcmToken = pref.getString("fcmToken", null)
                    if (storedFcmToken == null) {
                        val fcmToken = Firebase.messaging.token.await()
                        pref.edit {
                            putString("fcmToken", fcmToken)
                        }
                        Log.v("FCM Token", fcmToken)
                        // TODO: Send fcmToken to server
                    }
                }
                if (loginVM.showToast.value) {
                    if (loginVM.toastMessage.value != null) {
                        Toast.makeText(
                            context, loginVM.toastMessage.value, Toast.LENGTH_SHORT
                        ).show()
                        loginVM.showToast.value = false
                    }
                }
            } else if (loginVM.isLoggedInWithSuccess.value == false) {
                if (loginVM.showToast.value) {
                    if (loginVM.toastMessage.value != null) {
                        Toast.makeText(
                            context, loginVM.toastMessage.value, Toast.LENGTH_SHORT
                        ).show()
                        loginVM.showToast.value = false
                    }
                }
            }

            Text(
                text = "Forgot the password?",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .weight(1f)
                        .background(blackColor.copy(0.2f))
                )
                Text(
                    text = "or continue with",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .weight(1f)
                        .background(blackColor.copy(0.2f))
                )
            }

            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = null,
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        shape = RoundedCornerShape(12.dp),
                        color = blackColor.copy(0.2f)
                    )
                    .clip(RoundedCornerShape(12.dp))
                    .clickable {
                        getGoogleSignInClient(context).signOut().addOnCompleteListener {
                            authResultLauncher.launch(signInRequestCode)
                        }
                    }
                    .padding(horizontal = 24.dp, vertical = 15.dp)
                    .size(20.dp),
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Dont' have an account? ",
                    color = blackColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(text = "Sign up",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clip(RoundedCornerShape(12.dp)).clickable {
                        navController.navigate(Destination.SignUp.route)
                    }.padding(4.dp))
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

fun getGoogleSignInClient(context: Context): GoogleSignInClient {
    val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        //.requestIdToken(CLIENT_ID) // Request id token if you intend to verify google user from your backend server
        .requestEmail()
        .build()

    return GoogleSignIn.getClient(context, signInOptions)
}

class AuthResultContract : ActivityResultContract<Int, Task<GoogleSignInAccount>?>() {
    override fun createIntent(context: Context, input: Int): Intent =
        getGoogleSignInClient(context).signInIntent.putExtra("input", input)

    override fun parseResult(resultCode: Int, intent: Intent?): Task<GoogleSignInAccount>? {
        return when (resultCode) {
            Activity.RESULT_OK -> GoogleSignIn.getSignedInAccountFromIntent(intent)
            else -> null
        }
    }
}