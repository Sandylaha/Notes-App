package com.sandip.notesapp.repository

import androidx.lifecycle.LiveData
import com.sandip.notesapp.db.NoteDao
import com.sandip.notesapp.model.NoteEntity


class Repository(private val noteDao: NoteDao ) {
    val allData: LiveData<List<NoteEntity>> = noteDao.getNewestToOldestData()

    suspend fun insert(entityPerson: NoteEntity) {
        noteDao.insertDao(entityPerson)
    }

    suspend fun delete(entityPerson: NoteEntity){
        noteDao.deleteDao(entityPerson)
    }

    suspend fun update(entityPerson: NoteEntity){
        noteDao.updateDao(entityPerson)
    }

    fun searchDatabase(desc : String) : LiveData<List<NoteEntity>>{
        return noteDao.searchDatabase(desc)
    }

   suspend fun getDataById(reqId: Int): List<NoteEntity>{
       return noteDao.getDataById(reqId)
    }

    suspend fun deleteById(iD: Int){
        noteDao.deleteById(iD)
    }
}