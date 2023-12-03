package com.example.happibee.Data.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.happibee.Data.Dao.ApiarioDao
import com.example.happibee.Data.Model.Apiario

@Database(entities = [Apiario::class], version = 1)
abstract class HappiBeeDatabase : RoomDatabase() {
    abstract fun apiarioDao(): ApiarioDao
}