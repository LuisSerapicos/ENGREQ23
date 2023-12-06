package com.example.happibee.Data.UseCases.Apicultor

import com.example.happibee.Data.Repository.Apicultor.ApicultorRepository
import com.example.happibee.Data.Repository.Apicultor.ApicultorRepositoryImpl
import javax.inject.Inject

class GetApicultors @Inject constructor(private val repository: ApicultorRepositoryImpl){
    operator fun invoke() = repository.getApicultors()
}