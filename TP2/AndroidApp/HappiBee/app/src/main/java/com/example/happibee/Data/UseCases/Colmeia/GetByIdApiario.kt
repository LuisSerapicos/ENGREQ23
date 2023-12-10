package com.example.happibee.Data.UseCases.Colmeia

import com.example.happibee.Data.Repository.Apiario.ApiarioRepositoryImpl
import com.example.happibee.Data.Repository.Apiario.Colmeia.ColmeiaRepositoryImpl
import javax.inject.Inject

class GetByIdApiario @Inject constructor(private val repository: ColmeiaRepositoryImpl){
    operator fun invoke(id: Int) = repository.getByApiarioId(id)
}