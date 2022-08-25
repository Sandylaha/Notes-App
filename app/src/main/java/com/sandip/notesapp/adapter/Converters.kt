package com.sandip.notesapp.adapter

import android.database.CursorWindow
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream
import java.lang.reflect.Field
import java.sql.Date
import java.text.SimpleDateFormat

class Converters {

    @TypeConverter
    fun fromBitmap(bitmap: Bitmap?): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }

    @TypeConverter
    fun toBitmap(byteArray: ByteArray?): Bitmap? {
       return byteArray?.size?.let { BitmapFactory.decodeByteArray(byteArray, 0, it) }
    }



}