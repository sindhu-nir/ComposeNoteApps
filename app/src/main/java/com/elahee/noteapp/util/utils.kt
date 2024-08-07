package com.elahee.noteapp.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun fromDate(time:Long):String{
    val date= Date(time)
    val simpleDateFormat= SimpleDateFormat("EEE, d MMM hh:mm aaa", Locale.getDefault())

    return simpleDateFormat.format(date)
}