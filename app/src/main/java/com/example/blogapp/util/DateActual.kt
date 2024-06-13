package com.example.blogapp.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Calendar

class DateActual {
    @SuppressLint("SimpleDateFormat")
    fun getDateNow(): String {
        val simpleDateFormat = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val current = Calendar.getInstance()
        return simpleDateFormat.format(current.time)
    }
}