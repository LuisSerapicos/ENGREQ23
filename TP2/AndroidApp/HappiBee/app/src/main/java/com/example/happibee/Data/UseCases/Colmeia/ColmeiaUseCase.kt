package com.example.happibee.Data.UseCases.Colmeia

data class ColmeiaUseCase (
    val getColmeias: GetColmeias,
    val insertColmeia: InsertColmeia,
    val deleteColmeia: DeleteColmeia,
    val updateColmeia: UpdateColmeia,
    val getByIdApiario: GetByIdApiario,
)