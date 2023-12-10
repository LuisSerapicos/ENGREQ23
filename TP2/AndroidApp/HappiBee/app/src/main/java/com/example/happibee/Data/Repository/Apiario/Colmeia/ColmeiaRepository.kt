package com.example.happibee.Data.Repository.Apiario.Colmeia

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.happibee.Data.Model.Apiario
import com.example.happibee.Data.Model.Colmeia
import kotlinx.coroutines.flow.Flow

@Dao
interface ColmeiaRepository {
    @Insert
    suspend fun insertColmeia(colmeia: Colmeia)
    @Update
    suspend fun updateColmeia(colmeia: Colmeia)
    @Delete
    suspend fun deleteColmeia(colmeia: Colmeia)
    @Query("select * from colmeia")
    fun getColmeia(): Flow<List<Colmeia>>
    @Query("select * from colmeia where id=:id")
    suspend fun getByIdColmeia(id:Int):Colmeia
    @Query("select * from colmeia where apiarioId=:id")
    fun getByApiarioId(id:Int):Flow<List<Colmeia>>
}