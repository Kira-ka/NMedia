package ru.netology.nmedia.service

import android.os.Bundle
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson

class FCMService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        println(Gson().toJson(message))
    }

    override fun onNewToken(token: String) {
        println(token)
    }
}
