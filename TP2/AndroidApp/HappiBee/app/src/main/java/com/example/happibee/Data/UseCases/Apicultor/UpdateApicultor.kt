package com.example.happibee.Data.UseCases.Apicultor

import com.example.happibee.Data.Model.Apicultor
import com.example.happibee.Data.Repository.Apicultor.ApicultorRepository
import com.example.happibee.Data.Repository.Apicultor.ApicultorRepositoryImpl
import javax.inject.Inject

class UpdateApicultor @Inject constructor(private val repository: ApicultorRepositoryImpl) {
    suspend operator fun invoke(apicultor: Apicultor) = repository.updateApicultor(apicultor)
}