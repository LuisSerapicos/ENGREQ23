package com.example.happibee.Data.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.happibee.Data.Model.Apiario
import com.example.happibee.Data.Repository.Apiario.ApiarioRepository

@Database(entities = [Apiario::class], version = 1, exportSchema = false)
abstract class HappiBeeDatabase: RoomDatabase(){
    abstract fun apiarioDao(): ApiarioRepository
}