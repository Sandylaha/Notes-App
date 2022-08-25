package com.sandip.notesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.sandip.notesapp.db.NoteDatabase
import com.sandip.notesapp.model.NoteEntity
import com.sandip.notesapp.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application){

    val allData: LiveData<List<NoteEntity>>
    private val repository : Repository

    init {
        val dao = NoteDatabase.getDataBase(application).getData()
        repository = Repository(dao)
        allData = repository.allData
    }

    fun deleteNote (entityPerson: NoteEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(entityPerson)
    }

    fun deleteById (iD: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteById(iD)
    }




    fun updateNote(entityPerson: NoteEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(entityPerson)
    }

    fun addNote(entityPerson: NoteEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(entityPerson)
    }


    fun searchDatabase(desc: String) : LiveData<List<NoteEntity>>    {
        return repository.searchDatabase(desc)
    }

    suspend fun getDataById(reqId: Int) : List<NoteEntity>   {
        return repository.getDataById(reqId)
    }
}


