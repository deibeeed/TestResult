package com.example.testresultapp.ui.utils

sealed class Status {
    /**
     * emit for success status
     */
    data class Success<DATA>(val message: String, val data: DATA?): Status()

    /**
     * emit for failure status
     */
    data class Failure(val message: String? = null, val err: Throwable? = null): Status()
}