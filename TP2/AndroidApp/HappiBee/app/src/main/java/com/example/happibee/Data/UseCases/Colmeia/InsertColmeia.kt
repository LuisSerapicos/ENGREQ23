package com.example.happibee.Data.UseCases.Colmeia

import com.example.happibee.Data.Model.Apiario
import com.example.happibee.Data.Model.Colmeia
import com.example.happibee.Data.Repository.Apiario.ApiarioRepositoryImpl
import com.example.happibee.Data.Repository.Apiario.Colmeia.ColmeiaRepositoryImpl

class InsertColmeia constructor(private val repository: ColmeiaRepositoryImpl) {
    suspend operator fun invoke(colmeia: Colmeia) = repository.insertColmeia(colmeia)
}