package com.example.happibee.Data.UseCases.Apiario

import com.example.happibee.Data.Model.Apiario
import com.example.happibee.Data.Repository.Apiario.ApiarioRepositoryImpl

class InsertApiario constructor(private val repository: ApiarioRepositoryImpl) {
    //suspend operator fun invoke(apiario: Apiario) = repository.insertApiario(apiario)
}