package com.example.happibee.Data.UseCases.Apiario

import com.example.happibee.Data.Repository.Apiario.ApiarioRepositoryImpl
import javax.inject.Inject

class GetApiarios @Inject constructor(private val repository: ApiarioRepositoryImpl) {
    operator fun invoke() = repository.getApiarios()
}