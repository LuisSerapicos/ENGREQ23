package com.example.happibee.Data.UseCases.Inspecao

import com.example.happibee.Data.Model.Inspecao
import com.example.happibee.Data.Repository.Inspecao.InspecaoRepositoryImpl

class InsertInspecao constructor(private val repository: InspecaoRepositoryImpl) {
    suspend operator fun invoke(inspecao: Inspecao) = repository.insertInspecao(inspecao)
}