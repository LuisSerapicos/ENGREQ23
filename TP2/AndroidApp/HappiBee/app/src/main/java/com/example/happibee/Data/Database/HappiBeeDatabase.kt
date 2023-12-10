package com.example.happibee.Data.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.happibee.Data.Model.Apiario
import com.example.happibee.Data.Model.Apicultor
import com.example.happibee.Data.Model.Inspecao
import com.example.happibee.Data.Repository.Apiario.ApiarioRepository
import com.example.happibee.Data.Repository.Apicultor.ApicultorRepository
import com.example.happibee.Data.Repository.Inspecao.InspecaoRepository

@Database(entities = [Apiario::class, Apicultor::class, Inspecao::class], version = 7, exportSchema = false)
abstract class HappiBeeDatabase: RoomDatabase(){
    abstract fun apiarioDao(): ApiarioRepository
    abstract fun apicultorDao(): ApicultorRepository
    abstract fun inspecaoDao(): InspecaoRepository
}