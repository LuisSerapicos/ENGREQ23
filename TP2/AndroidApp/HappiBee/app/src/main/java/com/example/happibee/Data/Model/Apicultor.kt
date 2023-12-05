package com.example.happibee.Data.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "apicultor")
data class Apicultor(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val location: String,
    //@ColumnInfo(name = "email") val email: String,
    //@ColumnInfo(name = "phone") val phone: String,
    //@ColumnInfo(name = "password") val password: String
)