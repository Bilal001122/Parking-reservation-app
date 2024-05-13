package com.example.parkirapp.presentation.screens.on_boarding

import androidx.annotation.DrawableRes
import com.example.parkirapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Find Parking Places Around You Easily",
        description = "Easily locate nearby parking spots with our user-friendly app. Say goodbye to endless circling and hello to stress-free parking. Download now for hassle-free parking wherever you go!",
        image = R.drawable.search
    ),
    Page(
        title = "Book and Pay Parking Quickly & Safely",
        description = "Securely book and pay for parking in seconds. Skip the lines and enjoy hassle-free transactions. Download now for quick, safe parking!",
        image = R.drawable.payment
    ),
    Page(
        title = "Extend Parking Time As You Need",
        description = "Extend your parking time effortlessly whenever you need with our convenient app. Say goodbye to rushing back to your car and hello to flexibility. Download now for easy parking extensions!",
        image = R.drawable.time
    )
)
