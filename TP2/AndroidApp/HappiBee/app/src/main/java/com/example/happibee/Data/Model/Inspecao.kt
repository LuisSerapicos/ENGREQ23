package com.example.happibee.Data.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "inspecao")
data class Inspecao (
    val date: String,
    val observations: String,
    val apiarioId: Int,
    val apicultorId: Int? = 1,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
){
    constructor() : this("","", 0, 0, 0)
    constructor(date: String, observations: String, apiarioId: Int, apicultorId: Int?)
            : this(date, observations, apiarioId, apicultorId, 0)

}