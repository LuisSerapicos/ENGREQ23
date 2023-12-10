package com.example.happibee.Data.UseCases.Inspecao

import com.example.happibee.Data.Model.Inspecao
import com.example.happibee.Data.Repository.Apicultor.ApicultorRepositoryImpl
import com.example.happibee.Data.Repository.Inspecao.InspecaoRepositoryImpl
import javax.inject.Inject

class UpdateInspecao @Inject constructor(private val repository: InspecaoRepositoryImpl) {
    suspend operator fun invoke(inspecao: Inspecao) = repository.updateInspecao(inspecao)
}