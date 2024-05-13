package com.example.parkirapp.presentation.shared

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parkirapp.presentation.theme.whiteColor

@Composable
fun CustomButton(
    text: String,
    modifier: Modifier = Modifier,
    cornersRound: Dp = 100.dp,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.SemiBold,
    textColor: Color = whiteColor,
    background: Color = MaterialTheme.colorScheme.primary,
    padding: Dp = 4.dp,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(
            size = cornersRound
        ),
        onClick = onClick,
        contentPadding = PaddingValues(vertical = padding),
        colors = ButtonDefaults.buttonColors(
            containerColor = background
        ),

        ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = fontWeight,
                fontSize = fontSize,
                color = textColor
            ),
        )
    }
}