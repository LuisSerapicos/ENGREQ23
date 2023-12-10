package com.example.happibee.Data.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "inspecao")
data class Inspecao (
    val date: String,
    val observations: String,
    val apiarioId: Int? = 1,
    val apicultorId: Int? = 1,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
)