package com.example.happibee.Data.UseCases.Inspecao

import com.example.happibee.Data.Model.Inspecao
import com.example.happibee.Data.Repository.Inspecao.InspecaoRepositoryImpl
import javax.inject.Inject

class GetByIdInspecao @Inject constructor(private val repository: InspecaoRepositoryImpl) {
    suspend operator fun invoke(id: Int) = repository.getByIdInspecao(id)
}