package com.example.parkirapp.presentation.screens.payment.components

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.parkirapp.business_logic.vm.ReservationVM

@Composable
fun CardDetails(
    navController: NavController,
    date : String,
    startTime : String,
    endTime : String,
    total : Double,
    parkingId : Int,
    reservationVM: ReservationVM
) {
    var cardNumber by remember { mutableStateOf(TextFieldValue()) }
    var cardHolderName by remember { mutableStateOf(TextFieldValue()) }
    var expiryDate by remember { mutableStateOf(TextFieldValue()) }
    var cardCVV by remember { mutableStateOf(TextFieldValue()) }
    val openDialog = remember { mutableStateOf(false) }

    val context = LocalContext.current
    val pref = LocalContext.current.getSharedPreferences("parkir_sp", Context.MODE_PRIVATE)

    Column(modifier = Modifier.fillMaxSize()) {

        CreditCard(
            cardNumber = cardNumber,
            holderName = cardHolderName,
            expiryDate = expiryDate,
            cardCVV = cardCVV
        )

        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            item {
                InputTextField(
                    textFieldValue = cardNumber,
                    label = "Card Number",
                    keyboardType = KeyboardType.Number,
                    onTextChanged = { cardNumber = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    visualTransformation = CardNumberFilter
                )
            }

            item {
                InputTextField(
                    textFieldValue = cardHolderName,
                    label = "Card Holder Name",
                    onTextChanged = { cardHolderName = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                )
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    InputTextField(
                        textFieldValue = expiryDate,
                        label = "Expiry Date",
                        keyboardType = KeyboardType.Number,
                        onTextChanged = { expiryDate = it },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                    )
                    InputTextField(
                        textFieldValue = cardCVV,
                        label = "CVV",
                        keyboardType = KeyboardType.Number,
                        onTextChanged = { cardCVV = it },
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp)
                    )
                }
            }

            item {
                Button(
                    onClick = {
                        val authHeader = pref.getString("token", null) ?: ""
                        reservationVM.createReservation(
                            parkingId = parkingId,
                            date = date,
                            startHour = startTime,
                            endHour = endTime,
                            totalPrice = total,
                            authHeader = authHeader,
                        )
                        openDialog.value = true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
                ) {
                    Text(
                        text = "Confirm Payment",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 5.dp)
                    )
                }
            }
        }
        if (openDialog.value) {
            CustomAlertDialogPaymentSuccess(navController = navController)
        }
    }
}