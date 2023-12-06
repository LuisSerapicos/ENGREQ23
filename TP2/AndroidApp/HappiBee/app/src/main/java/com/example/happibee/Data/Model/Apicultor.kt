package com.example.happibee.Data.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "apicultor")
data class Apicultor(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val email: String,
    val phone: String,
    val password: String
)