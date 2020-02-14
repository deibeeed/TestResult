package com.example.testresultapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * this view model will show how we can use kotlin's default result
 */
class MainViewModel : ViewModel() {
    private val _result = MutableLiveData<Result<String>>()
    val result: LiveData<Result<String>>
        get() = _result

    private var counter = 0

    fun incrementCount() {
        if (counter == 10) {
            _result.value = Result.failure(Throwable("you have reached the limit"))
            return
        }

        counter++

        _result.value = Result.success("counter incremented")
    }
}
