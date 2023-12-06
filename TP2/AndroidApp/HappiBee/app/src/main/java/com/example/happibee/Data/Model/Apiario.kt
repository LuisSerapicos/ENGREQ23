package com.example.happibee.Data.Model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "apiario")
data class Apiario(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val location: String,
    val longitude: String? = "37.85",
    val latitude: String? = "-7.26",
    val apicultorId: Int? = 1
)