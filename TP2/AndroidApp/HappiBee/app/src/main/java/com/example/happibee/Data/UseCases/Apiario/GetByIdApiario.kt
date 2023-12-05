package com.example.happibee.Data.UseCases.Apiario

import com.example.happibee.Data.Repository.Apiario.ApiarioRepositoryImpl
import javax.inject.Inject

class GetByIdApiario @Inject constructor(private val repository: ApiarioRepositoryImpl){
    suspend operator fun invoke(id: Int) = repository.getByIdApiario(id)
}