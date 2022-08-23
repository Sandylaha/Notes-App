package com.sandip.notesapp.model

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Note")
class NoteEntity(
    @ColumnInfo(name = "Title") val title: String? = null,
    @ColumnInfo(name = "Body") val body: String? = null,
    @ColumnInfo(name = "TickDesc") val tickDesc: String? = null,
    @ColumnInfo(name = "URL") val url: String? = null,
    @ColumnInfo(name = "Date") val date: String? = null,
    @ColumnInfo(name = "Time") val time: String? = null,
    @ColumnInfo(name = "Location") val location: String? = null,
    @ColumnInfo(name = "Color") val clr: Int = 0,
    @ColumnInfo(name="image", typeAffinity = ColumnInfo.BLOB) val image: Bitmap? = null,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,










    )