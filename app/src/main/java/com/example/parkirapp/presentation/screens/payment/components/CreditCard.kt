package com.example.parkirapp.presentation.screens.payment.components
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CreditCard(
    cardNumber: TextFieldValue,
    holderName: TextFieldValue,
    expiryDate: TextFieldValue,
    cardCVV: TextFieldValue
) {

    // Mutable state to track the flip state of the card
    var backSwitch by remember { mutableStateOf(false) }

    // Mutable state to track the detected card type (Visa, Mastercard, etc.)
    var cardType by remember { mutableStateOf(Card.None) }

    // Calculate the length of the card number and mask it for display
    val length = if (cardNumber.text.length > 16) 16 else cardNumber.text.length
    val maskedNumber =
        remember { "*****************" }.replaceRange(0..length, cardNumber.text.take(16))


    val cvv = if (cardCVV.text.length > 3) 3 else cardCVV.text.length
    val maskedCVV = remember { "*".repeat(3) }.replaceRange(0 until cvv, cardCVV.text.take(3))

    // Determine whether to switch to the back side of the card based on CVV length
    if (cardCVV.text.length == 1 && !backSwitch) {
        backSwitch = true
    } else if (cardCVV.text.length == 2) {
        backSwitch = true
    } else if (cardCVV.text.length == 3) {
        backSwitch = false
    }

    // Detect and set the card type logo based on the card number's first digit
    cardType = when {
        cardNumber.text.isNotEmpty() -> {
            when (cardNumber.text.take(2)) {
                "30", "36", "38" -> Card.DinersClub
                "40" -> Card.Visa
                "50", "51", "52", "53", "54", "55" -> Card.Mastercard
                "56","57", "58", "63", "67" -> Card.Maestro
                "60" -> Card.RuPay
                "37" -> Card.AmericanExpress
                else -> Card.None
            }
        }

        else -> Card.None
    }

    // Set the card's background color based on its type
    val animatedColor = animateColorAsState(
        targetValue =
        when (cardType) {
            Card.Visa -> {
                Color(0xFF1C478B)
            }

            Card.Mastercard -> {
                Color(0xFF3BB9A1)
            }

            Card.RuPay -> {
                Color(0xFFB2B1FD)
            }

            Card.AmericanExpress -> {
                Color(0xFFA671FC)
            }

            Card.Maestro -> {
                Color(0xFF99BEF8)
            }

            Card.DinersClub -> {
                Color(0xFFFC4444)
            }
            else -> {
                MaterialTheme.colorScheme.primary
            }
        },
        label = ""
    )

    Box {
        Surface(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(200.dp)
                .graphicsLayer(
                    rotationY = animateFloatAsState(
                        if (backSwitch) 180f else 0f,
                        label = "",
                        animationSpec = tween(500),
                    ).value,
                    translationY = 0f
                )
                .clickable {
                    backSwitch = !backSwitch
                },
            shape = RoundedCornerShape(20.dp),
            color = animatedColor.value,
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                AnimatedVisibility(visible = !backSwitch) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                        ) {
                        AnimatedVisibility(visible = cardType != Card.None,
                            modifier = Modifier
                                .padding(start = 12.dp, top = 10.dp)
                               ) {
                            Image(
                                painter = painterResource(id = cardType.image),
                                contentDescription = "Card Image",
                                modifier = Modifier.size(80.dp)
                            )
                        }

                        Text(
                            text = maskedNumber.chunked(4).joinToString(" "),
                            style = MaterialTheme.typography.bodyMedium,
                            maxLines = 1,
                            color = Color.White,
                            modifier = Modifier
                                .animateContentSize(spring())
                                .padding(bottom = 20.dp)
                        )

                        Row {
                            Column {
                                Text(
                                    text = "Card Holder Name",
                                    color = Color.White,
                                    fontSize = 14.sp,
                                    modifier = Modifier
                                        .padding(horizontal = 16.dp)
                                )
                                Text(
                                    text = holderName.text,
                                    color = Color.White,
                                    fontSize = 14.sp,
                                    modifier = Modifier
                                        .animateContentSize(TweenSpec(300))
                                        .padding(start = 16.dp)
                                )
                            }

                            Column {
                                Text(
                                    text = "Expiry Date",
                                    color = Color.White,
                                    fontSize = 14.sp,
                                    modifier = Modifier
                                        .padding(horizontal = 16.dp)
                                )

                                Text(
                                    text = expiryDate.text.take(4).chunked(2).joinToString(" / "),
                                    color = Color.White,
                                    fontSize = 14.sp,
                                    modifier = Modifier
                                        .animateContentSize(TweenSpec(300))
                                        .padding(start = 16.dp)
                                )
                            }

                        }


                    }
                }

                AnimatedVisibility(visible = backSwitch) {
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .height(50.dp)
                        .background(
                            Color.Black
                        )
                        .fillMaxWidth())
                }
            }
        }

        AnimatedVisibility(
            visible = backSwitch,
            modifier = Modifier
                .padding(end = 50.dp, bottom = 50.dp)
                .align(Alignment.BottomEnd)
        ) {
            Box(
                modifier = Modifier
                    .defaultMinSize(minWidth = 60.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = maskedCVV,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    modifier = Modifier
                        .animateContentSize(TweenSpec(300))
                        .padding(vertical = 4.dp, horizontal = 16.dp)

                )
            }

        }
    }
}