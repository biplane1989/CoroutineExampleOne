package com.example.coroutineexampleone

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

class HomeViewModel : ViewModel() {

     val TAG = "TAG"
     val repository = Repository()
     private val _number = MutableLiveData<Int>()
     val number: LiveData<Int> = _number

     init {
     }

     var job: Job? = null

     fun start() {
         job = viewModelScope.launch() {
             Log.d(TAG, "start:  lauch")
             val result = repository.doSomethingA()
             Log.d(TAG, "set lauch ")
             _number.value = result
             Log.d(TAG, "end: lauch")
         }
     }

     fun stop() {
         job?.cancel()
         Log.d(TAG, "JOB stop: ")
     }


     fun publichser() = flow<Int> {
         for (i in 0 until 10) {
             delay(200)
             Log.d(TAG, "publichser: emit")
             emit(i)
         }
     }

     fun runCollect() {
         viewModelScope.launch {
             publichser().filter {
                 //start operator
                 Log.d(TAG, "runCollect: $it")
                 it % 2 == 0
             }.map {
                 Log.d(TAG, "runCollect: $it")
                 it * it
             } // end operator
                 // recever
                 .collect {
                     Log.d(TAG, "runCollect: $it")
                 }
         }
     }



}