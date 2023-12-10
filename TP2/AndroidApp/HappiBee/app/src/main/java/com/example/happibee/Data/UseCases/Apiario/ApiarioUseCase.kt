package com.example.happibee.Data.UseCases.Apiario

data class ApiarioUseCase (
    val getApiarios: GetApiarios,
    val insertApiario: InsertApiario,
    val deleteApiario: DeleteApiario,
    val updateApiario: UpdateApiario,
    val getByIdApiario: GetByIdApiario,
    val getByApicultorId: GetByApicultorId
)