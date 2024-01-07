package com.example.happibee.Data.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "colmeia")
data class Colmeia(
    val nabelhas: Int?,
    val nomeColmeia: String,
    val longitude: Double? = 37.85,
    val latitude: Double? = -7.26,
    val apiarioId: Int? = 1,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
){
    constructor() : this(null,"", null, null, null)

}