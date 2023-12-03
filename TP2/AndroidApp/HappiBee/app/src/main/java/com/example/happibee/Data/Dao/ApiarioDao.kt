package com.example.happibee.Data.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.happibee.Data.Model.Apiario
import kotlinx.coroutines.flow.Flow

@Dao
interface ApiarioDao {
    @Query("SELECT * FROM apiario")
    fun getAll(): Flow<List<Apiario>>

    @Query("SELECT * FROM apiario WHERE id IN (:apiarioIds)")
    fun loadAllByIds(apiarioIds: IntArray): List<Apiario>

    @Query("SELECT * FROM apiario WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): Apiario

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: Apiario)

    @Delete
    fun delete(user: Apiario)
}