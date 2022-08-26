package com.sandip.notesapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sandip.notesapp.model.NoteEntity

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDao(entityPerson: NoteEntity)

    @Update
    suspend fun updateDao(entityPerson: NoteEntity)

    @Delete
    suspend fun deleteDao(entityPerson: NoteEntity)

    @Query("DELETE from Note where id = :iD")
     suspend fun deleteById(iD:Int)


    @Query("select * from Note order by id DESC")
    fun getNewestToOldestData(): LiveData<List<NoteEntity>>

//    @Query("select * from Note order by id ASC")
//    fun getOldestToNewestData(): LiveData<List<NoteEntity>>
//
//    @Query("select * from Note order by Title ASC")
//    fun getByTitleAscData(): LiveData<List<NoteEntity>>

    @Query("select * from Note where id = :reqId")
    suspend fun getDataById(reqId:Int): List<NoteEntity>


    @Query("Select * from Note where Title like  :desc")
    fun searchDatabase(desc : String) : LiveData<List<NoteEntity>>


}