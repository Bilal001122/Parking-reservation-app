package com.example.parkirapp.ui.screens.login

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.parkirapp.R
import com.example.parkirapp.ui.navigation.Destination
import com.example.parkirapp.ui.shared.CustomButton
import com.example.parkirapp.ui.theme.blackColor
import com.example.parkirapp.ui.theme.whiteColor

@Composable
fun LoginScreen(navController: NavController) {

    val emailFieldValue = remember {
        mutableStateOf("")
    }

    val passwordFieldValue = remember {
        mutableStateOf("")
    }

    val checkBoxState = remember {
        mutableStateOf(false)
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
            OutlinedTextField(
                value = emailFieldValue.value,
                maxLines = 1,
                textStyle = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = Color.Black,
                ),
                shape = RoundedCornerShape(
                    size = 10.dp,
                ),
                colors = TextFieldDefaults.colors(
                    disabledContainerColor = blackColor.copy(alpha = 0.04f),
                    unfocusedContainerColor = blackColor.copy(alpha = 0.04f),
                    focusedContainerColor = MaterialTheme.colorScheme.primary.copy(0.1f),
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = MaterialTheme.colorScheme.primary,
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.email),
                        contentDescription = null
                    )
                },
                onValueChange = { newValue: String ->
                    emailFieldValue.value = newValue
                },
                placeholder = {
                    Text(text = "Email", fontSize = 14.sp, textAlign = TextAlign.Center)
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = passwordFieldValue.value,
                maxLines = 1,
                textStyle = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = Color.Black,
                ),
                shape = RoundedCornerShape(
                    size = 10.dp,
                ),
                colors = TextFieldDefaults.colors(
                    disabledContainerColor = blackColor.copy(alpha = 0.04f),
                    unfocusedContainerColor = blackColor.copy(alpha = 0.04f),
                    focusedContainerColor = MaterialTheme.colorScheme.primary.copy(0.1f),
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = MaterialTheme.colorScheme.primary,
                ),
                leadingIcon = {
                    Icon(painter = painterResource(id = R.drawable.lock), contentDescription = null)
                },
                onValueChange = { newValue: String ->
                    passwordFieldValue.value = newValue
                },
                placeholder = {
                    Text(text = "Password", fontSize = 14.sp, textAlign = TextAlign.Center)
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {

                Checkbox(
                    checked = checkBoxState.value,
                    onCheckedChange = { newValue ->
                        checkBoxState.value = newValue
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colorScheme.primary,
                        checkmarkColor = whiteColor,
                        uncheckedColor = MaterialTheme.colorScheme.primary,
                    ),
                    modifier = Modifier.clip(RoundedCornerShape(20.dp))
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
                navController.navigate(Destination.ParkingList.route)
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
                    .padding(horizontal = 24.dp, vertical = 15.dp)
                    .size(20.dp),
            )

            Row {
                Text(
                    text = "Dont' have an account? ",
                    color = blackColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Sign up",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable {
                        navController.navigate(Destination.SignUp.route)
                    }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}
