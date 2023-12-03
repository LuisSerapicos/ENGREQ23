package com.example.happibee.Data.Model

data class PedidoTransumancia (
    val id: Int,
    val idApiarioOrigem: Int,
    val idApiarioDestino: Int,
    val autorizacaoDGAV: Boolean,
    val condicionantesLocalizacao: String?
)