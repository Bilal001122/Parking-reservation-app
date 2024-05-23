package com.example.parkirapp.business_logic.services

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushNotificationService : FirebaseMessagingService(){
    override fun onNewToken(token: String) {
        super.onNewToken(token)

        // Update token to server
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // Respond to received message
    }
}