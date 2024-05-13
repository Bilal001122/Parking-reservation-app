package com.example.parkirapp.presentation.screens.signup.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parkirapp.presentation.theme.blackColor

@Composable
fun CustomTextFieldWithLeadingIcon(
    hintText: String,
    field: MutableState<String>,
    leadingIcon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text,
    showPassword: Boolean? = null,
) {
    OutlinedTextField(
        value = field.value,
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
            Icon(imageVector = leadingIcon, contentDescription = null)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        onValueChange = { newValue: String ->
            field.value = newValue
        },
        placeholder = {
            Text(text = hintText, fontSize = 14.sp, textAlign = TextAlign.Center)
        },
        modifier = Modifier
            .fillMaxWidth(),
        visualTransformation = if (showPassword == true) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}