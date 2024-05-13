package com.example.parkirapp.presentation.screens.profile

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
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ProfileScreen(navController: NavController) {
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
        Column (){
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
                Text(text = "John Doe")
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
                Text(text = "12/05/2024")
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
                Text(text = "Male")
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
                Text(text = "JohnDoe@gmail.com")
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
                Text(text = "08123456789")
            }
        }
        Column {
            TextButton(
                onClick = {
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
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Favorites",
                    tint = Color.White
                )
            }
        }
    }
}