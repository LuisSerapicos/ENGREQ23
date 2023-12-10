package com.example.happibee.Data.Repository.Apiario

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.happibee.Data.Model.Apiario
import kotlinx.coroutines.flow.Flow

@Dao
interface ApiarioRepository {
    @Insert
    suspend fun insertApiario(apiario: Apiario)
    @Update
    suspend fun updateApiario(apiario: Apiario)
    @Delete
    suspend fun deleteApiario(apiario: Apiario)
    @Query("select * from apiario")
    fun getApiarios(): Flow<List<Apiario>>
    @Query("select * from apiario where id=:id")
    suspend fun getByIdApiario(id:Int):Apiario
    @Query("select * from apiario where apicultorId=:id")
    fun getByApicultorId(id:Int):Flow<List<Apiario>>
}