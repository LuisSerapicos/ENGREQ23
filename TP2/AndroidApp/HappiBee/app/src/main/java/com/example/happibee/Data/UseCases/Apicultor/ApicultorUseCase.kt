package com.example.happibee.Data.UseCases.Apicultor

data class ApicultorUseCase (
    val getApicultors: GetApicultors,
    val insertApicultor: InsertApicultor,
    val updateApicultor: UpdateApicultor,
    val getByIdApicultor: GetByIdApicultor,
    val getApiariosByIdApicultor: GetApiariosByIdApicultor,
    val login: LoginUseCase
)