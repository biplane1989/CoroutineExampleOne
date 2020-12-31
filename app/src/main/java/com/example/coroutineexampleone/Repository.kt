package com.example.coroutineexampleone

import android.util.Log
import androidx.annotation.RestrictTo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.coroutineexampleone.test.Event
import com.example.coroutineexampleone.test.FileManager
import kotlinx.coroutines.*
import kotlin.math.log

class Repository {

    val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    val TAG = "TAG"
    suspend fun doSomethingA(): Int = coroutineScope {

//        launch {
//            Log.d(TAG, "A Call B: ")
//            val b = doSomethingB()
//            Log.d(TAG, "A Call B Done")
//        }

        val valueB = async(Dispatchers.Default) {
            Log.d(TAG, "A Call B: ")
            val b = doSomethingB()
            Log.d(TAG, "A Call B Done")
            b
        }

        val valueC = async {
            Log.d(TAG, "A Call C")
            val c = doSomethingC()
            Log.d(TAG, "A Call C Done")
            c
        }


        Log.d(TAG, "A Done")
        val b = valueB.await()
        val c = valueC.await()

        b + c
    }

    fun doSomethingB(): Int {    // khong bi cancel khi Job.cancel()
        Log.d(TAG, "B started")
        for (i in 0 until 5) {
            Thread.sleep(400)
            Log.d(TAG, "B doing")
//            if (!isActive) return@withContext 0     // khi bi cancel job thi tag se dung
        } // heavy task
        Log.d(TAG, "B end")
        return 8
    }

    suspend fun doSomethingC(): Int = withContext(Dispatchers.Default) {
        Log.d(TAG, "C started")
        for (i in 0 until 5) {
            Thread.sleep(400)
            Log.d(TAG, "C doing $isActive")
//            if (!isActive) return@withContext 0     // khi bi cancel job thi tag se dung
        }
        Log.d(TAG, "C end")
        10
    }

}