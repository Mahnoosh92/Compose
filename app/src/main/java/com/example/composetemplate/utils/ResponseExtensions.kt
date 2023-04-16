package com.example.composetemplate.utils

import com.example.composetemplate.data.models.local.MyError
import com.google.gson.Gson
import retrofit2.Response

fun Response<*>.getApiError(): MyError? {
    return try {
        val errorJsonString = this.errorBody()?.string()
        Gson().fromJson(errorJsonString, MyError::class.java)
    } catch (exception: Exception) {
        exception.printStackTrace()
        null
    }
}