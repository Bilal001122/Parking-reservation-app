package com.example.parkirapp.ui.screens.payment.components

import androidx.annotation.DrawableRes
import com.example.parkirapp.R

enum class Card(
    @DrawableRes val image: Int
) {
    None(R.drawable.ic_visa),
    Visa(R.drawable.ic_visa),
    Mastercard(R.drawable.ic_mastercard),
    RuPay(R.drawable.rupay_logo),
    AmericanExpress(R.drawable.amex_logo),
    Maestro(R.drawable.maestro),
    DinersClub(R.drawable.diner_clubs)
}