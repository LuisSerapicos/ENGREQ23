package com.example.happibee.Data.Model

import androidx.room.Embedded
import androidx.room.Relation

data class ApicultorWithApiarios(
    @Embedded val apicultor: Apicultor,
    @Relation(
        parentColumn = "id",
        entityColumn = "apicultorId"
    )
    val apiarios: List<Apiario>
)