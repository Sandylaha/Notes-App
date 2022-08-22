package com.sandip.notesapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time
import java.util.*

@Entity(tableName = "Note")
class NoteEntity(
    @ColumnInfo(name = "Title") val title: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

//
//    @ColumnInfo(name = "Body") val body: String,
//    @ColumnInfo(name = "TickDesc") val tickDesc: String,
//    @ColumnInfo(name = "URL") val url: String,
//    @ColumnInfo(name = "Date") val date: Date,
//    @ColumnInfo(name = "Time") val time: Time,
//    @ColumnInfo(name = "Location") val location: String,
//    @ColumnInfo(name = "Color") val clr: String,
//    @ColumnInfo(name="image", typeAffinity = ColumnInfo.BLOB) val image: ByteArray,
//










    )