package com.example.parkirapp.ui.screens.signup

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.parkirapp.data.api.vm.RegistrationVM
import com.example.parkirapp.ui.screens.signup.components.CustomAlertDialog
import com.example.parkirapp.ui.screens.signup.components.CustomTextField
import com.example.parkirapp.ui.screens.signup.components.CustomTextFieldWithDatePicker
import com.example.parkirapp.ui.screens.signup.components.CustomTextFieldWithLeadingIcon
import com.example.parkirapp.ui.shared.CustomButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController, registrationVM: RegistrationVM) {
    val fullNameField = remember { mutableStateOf("") }
    val emailField = remember { mutableStateOf("") }
    val dateOfBirthField = remember { mutableStateOf("") }
    val phoneNumberField = remember { mutableStateOf("") }
    val passwordField = remember { mutableStateOf("") }
    val expanded = remember { mutableStateOf(false) }
    val options = listOf("Male", "Female")
    val selectedOption = remember { mutableStateOf(options[0]) }
    val openDialog = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.weight(2f),
        ) {
            Text(
                text = "Create your\nAccount",
                fontWeight = FontWeight.SemiBold,
                fontSize = 36.sp,
                lineHeight = 40.sp,
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .weight(8f)
                .verticalScroll(rememberScrollState()),
        ) {
            CustomTextField(
                hintText = "Full Name",
                field = fullNameField,
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomTextFieldWithDatePicker(
                hintText = "Date of Birth",
                field = dateOfBirthField,
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomTextFieldWithLeadingIcon(
                hintText = "Phone Number",
                field = phoneNumberField,
                leadingIcon = Icons.Filled.Phone,
                keyboardType = KeyboardType.Number,
                showPassword = true,
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomTextFieldWithLeadingIcon(
                hintText = "Email",
                field = emailField,
                leadingIcon = Icons.Filled.Email,
                keyboardType = KeyboardType.Email,
                showPassword = true,
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomTextFieldWithLeadingIcon(
                hintText = "Password",
                field = passwordField,
                leadingIcon = Icons.Filled.Lock,
                keyboardType = KeyboardType.Password,
                showPassword = false,
            )
            Spacer(modifier = Modifier.height(16.dp))
            ExposedDropdownMenuBox(
                expanded = expanded.value,
                onExpandedChange = {
                    expanded.value = it
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                TextField(
                    value = selectedOption.value,
                    onValueChange = {
                        selectedOption.value = it
                    },
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value)
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    textStyle = MaterialTheme.typography.labelMedium,
                    placeholder = {
                        Text("Gender")
                    }
                )

                ExposedDropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = {
                        expanded.value = false
                    },
                    modifier = Modifier
                        .background(Color.White)
                        .exposedDropdownSize()
                ) {
                    options.forEach { it ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = it,
                                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                                )
                            },
                            onClick = {
                                selectedOption.value = it
                                expanded.value = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        )
                    }
                }

            }
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.weight(1.5f),
        ) {
            CustomButton(
                text = "Sign Up", modifier = Modifier.fillMaxWidth(),
                padding = 16.dp,
                fontWeight = FontWeight.Medium
            ) {
                when {
                    fullNameField.value.isEmpty() -> {
                        Toast.makeText(context, "Full name cannot be empty", Toast.LENGTH_SHORT)
                            .show()
                    }

                    dateOfBirthField.value.isEmpty() -> {
                        Toast.makeText(
                            context,
                            "Date of birth cannot be empty",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    phoneNumberField.value.isEmpty() -> {
                        Toast.makeText(
                            context,
                            "Phone number cannot be empty",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    emailField.value.isEmpty() -> {
                        Toast.makeText(context, "Email cannot be empty", Toast.LENGTH_SHORT)
                            .show()
                    }

                    passwordField.value.isEmpty() -> {
                        Toast.makeText(context, "Password cannot be empty", Toast.LENGTH_SHORT)
                            .show()
                    }

                    else -> {
                        registrationVM.signUpUser(
                            emailField.value,
                            passwordField.value,
                            fullNameField.value,
                            dateOfBirthField.value,
                            phoneNumberField.value,
                            selectedOption.value
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        if (registrationVM.isLoading.value == true) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
    }

    if (registrationVM.showToast.value) {
        if (registrationVM.toastMessage.value != null) {
            Toast.makeText(context, registrationVM.toastMessage.value, Toast.LENGTH_SHORT).show()
            registrationVM.showToast.value = false
        }
    }

    if (registrationVM.registrationSuccess.value == true) {
        openDialog.value = true
    }

    if (openDialog.value) {
        CustomAlertDialog(navController, openDialog)
        registrationVM.registrationSuccess.value = null
        registrationVM.showToast.value = false
        registrationVM.toastMessage.value = null
        registrationVM.isLoading.value = null
    }

    BackHandler {
        navController.popBackStack()
        registrationVM.registrationSuccess.value = null
        registrationVM.showToast.value = false
        registrationVM.toastMessage.value = null
        registrationVM.isLoading.value = null
    }
}


