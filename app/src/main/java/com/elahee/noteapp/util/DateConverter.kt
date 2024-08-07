package com.elahee.noteapp.util

import androidx.room.TypeConverter
import java.util.Date

class DateConverter(){

    @TypeConverter
    fun timeStampFromDate(date:Date):Long{
        return date.time
    }
    @TypeConverter
    fun dateFromTimeStamp(time:Long):Date{
        return Date(time)
    }
}