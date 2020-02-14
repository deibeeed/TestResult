package com.example.testresultapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testresultapp.ui.utils.Status

class MainViewModel2 : ViewModel() {
    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status

    private var counter = 0

    fun incrementCounter() {
        if (counter == 10) {
            _status.value = Status.Failure(err = Throwable("limit reached"))
            return
        }

        counter++

        _status.value = Status.Success(data = true, message = "counter incremented successfully")
    }
}
