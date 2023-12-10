package com.example.happibee.Data.UseCases.Inspecao

data class InspecaoUseCase (
    val getInspecoes: GetInspecoes,
    val insertInspecao: InsertInspecao,
    val updateInspecao: UpdateInspecao,
    val getByIdInspecao: GetByIdInspecao,
    val getInspecaoByApiario: GetInspecaoByApiario
)