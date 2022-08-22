package com.sandip.notesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sandip.notesapp.R

class Note : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
    }
}