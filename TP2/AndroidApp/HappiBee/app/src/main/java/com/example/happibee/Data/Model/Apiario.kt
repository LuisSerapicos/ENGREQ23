package com.example.happibee.Data.Model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "apiario")
data class Apiario(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val location: String,
    val longitude: String? = "lng",
    val latitude: String? = "lat",
    val apicultorId: Int? = 1
)