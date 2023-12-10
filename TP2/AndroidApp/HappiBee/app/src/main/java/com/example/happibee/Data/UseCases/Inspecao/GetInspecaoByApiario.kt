package com.example.happibee.Data.UseCases.Inspecao

import com.example.happibee.Data.Repository.Inspecao.InspecaoRepositoryImpl
import javax.inject.Inject

class GetInspecaoByApiario @Inject constructor(private val repository: InspecaoRepositoryImpl) {
    operator fun invoke(id: Int) = repository.getInspecaoByApiario(id)
}