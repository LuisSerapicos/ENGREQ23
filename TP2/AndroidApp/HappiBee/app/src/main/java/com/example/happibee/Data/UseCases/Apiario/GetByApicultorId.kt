package com.example.happibee.Data.UseCases.Apiario

import com.example.happibee.Data.Repository.Apiario.ApiarioRepositoryImpl
import javax.inject.Inject

class GetByApicultorId @Inject constructor(private val repository: ApiarioRepositoryImpl) {
    operator fun invoke(id: Int) = repository.getByApicultorId(id)
}