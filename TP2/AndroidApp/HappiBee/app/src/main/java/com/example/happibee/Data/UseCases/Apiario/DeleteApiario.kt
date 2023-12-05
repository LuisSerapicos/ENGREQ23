package com.example.happibee.Data.UseCases.Apiario

import com.example.happibee.Data.Model.Apiario
import com.example.happibee.Data.Repository.Apiario.ApiarioRepositoryImpl
import javax.inject.Inject

class DeleteApiario @Inject constructor(private val repository: ApiarioRepositoryImpl) {
    suspend operator fun invoke(apiario: Apiario) = repository.deleteApiario(apiario)
}