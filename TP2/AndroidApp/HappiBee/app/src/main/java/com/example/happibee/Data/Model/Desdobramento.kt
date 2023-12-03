package com.example.happibee.Data.Model

data class Desdobramento (
    val id: Int,
    val idApiario: Int,
    val elementos: List<ElementoDesdobramento>
)

data class ElementoDesdobramento(
    val id: Int,
    val nome: String,
    val tipo: TipoElementoDesdobramento
)

enum class TipoElementoDesdobramento {
    ELEMENTO_4,
    ELEMENTO_5
}