package com.example.happibee.Data.Repository.Inspecao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.happibee.Data.Model.Apicultor
import com.example.happibee.Data.Model.ApicultorWithApiarios
import com.example.happibee.Data.Model.Inspecao
import kotlinx.coroutines.flow.Flow

@Dao
interface InspecaoRepository {
    @Insert
    suspend fun insertInspecao(inspecao: Inspecao)
    @Update
    suspend fun updateInspecao(inspecao: Inspecao)
    @Query("select * from inspecao")
    fun getInspecoes(): Flow<List<Inspecao>>
    @Query("select * from inspecao where id=:id")
    suspend fun getByIdInspecao(id:Int): Inspecao
    @Query("select * from inspecao where apiarioId=:id")
    fun getInspecaoByApiario(id:Int): Flow<List<Inspecao>>
}