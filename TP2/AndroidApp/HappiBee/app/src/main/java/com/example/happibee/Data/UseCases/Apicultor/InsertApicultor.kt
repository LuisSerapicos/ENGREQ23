package com.example.happibee.Data.UseCases.Apicultor

import com.example.happibee.Data.Model.Apicultor
import com.example.happibee.Data.Repository.Apicultor.ApicultorRepository
import com.example.happibee.Data.Repository.Apicultor.ApicultorRepositoryImpl

class InsertApicultor constructor(private val repository: ApicultorRepositoryImpl) {
    suspend operator fun invoke(apicultor: Apicultor) = repository.insertApicultor(apicultor)
}