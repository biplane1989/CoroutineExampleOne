package com.example.coroutineexampleone.test

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.security.cert.CertPath
import kotlin.random.Random
import kotlin.random.Random.Default.nextBoolean


enum class Event {
    OPEN, FAIL, DELETE
}

object FileManager {

    suspend fun delete(path: String, listener: (Event) -> Unit) = coroutineScope {
        launch {
            listener(Event.OPEN)
            delay(200)
            if (Random.nextBoolean()) {
                listener(Event.DELETE)
            } else listener(Event.FAIL)
        }
    }
}