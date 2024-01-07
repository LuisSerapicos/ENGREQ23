package com.example.happibee.Data.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "apiario")
data class Apiario(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String?,
    val location: String,
    val longitude: Double? = 37.85,
    val latitude: Double? = -7.26,
    val apicultorId: Int? = 1,
){
    constructor(name: String, location: String, longitude: Double, latitude: Double, apicultorId: Int)
            : this(0, name, location, longitude, latitude, apicultorId)
    constructor(id: Int, name: String?, location: String, longitude: Double, latitude: Double)
            : this(id, name, location, longitude, latitude, null)
    constructor() : this(0, "", "", null, null,null)
}