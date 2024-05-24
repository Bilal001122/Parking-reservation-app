package com.example.parkirapp.presentation.screens.payment.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.parkirapp.presentation.navigation.Destination
import com.example.parkirapp.presentation.shared.CustomButton

@Composable
fun CustomAlertDialogPaymentSuccess(navController: NavController) {
    AlertDialog(
        containerColor = Color.White,
        icon = {
            Icon(
                Icons.Filled.CheckCircle,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .size(50.dp)
                    .background(MaterialTheme.colorScheme.primary),
                contentDescription = null,
            )
        },
        title = {
            Text(
                text = "Successful!",
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary
            )
        },
        text = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Successfully made payment for your reservation", textAlign = TextAlign.Center)
            }
        },
        onDismissRequest = {
        },
        confirmButton = {
            CustomButton(
                text = "Go to Bookings", modifier = Modifier.fillMaxWidth(),
                padding = 12.dp,
                fontWeight = FontWeight.Medium
            ) {
                navController.navigate(Destination.Bookings.route)
            }
        },
    )
}