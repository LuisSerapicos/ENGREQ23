package com.example.happibee.Data.Repository.Apicultor

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.happibee.Data.Model.Apiario
import com.example.happibee.Data.Model.Apicultor
import com.example.happibee.Data.Model.ApicultorWithApiarios
import kotlinx.coroutines.flow.Flow

@Dao
interface ApicultorRepository {
    @Insert
    suspend fun insertApicultor(apicultor: Apicultor)
    @Update
    suspend fun updateApicultor(apicultor: Apicultor)
    @Query("select * from apicultor")
    fun getApicultors(): Flow<List<Apicultor>>
    @Query("select * from apicultor where id=:id")
    suspend fun getByIdApicultor(id:Int): Apicultor
    @Transaction
    @Query("select * from apicultor where id = :id")
    suspend fun getApicultorWithApiarios(id: Int): ApicultorWithApiarios
    @Query("select * from apicultor where name = :name AND password = :password")
    fun login(name: String, password: String): Apicultor?
}