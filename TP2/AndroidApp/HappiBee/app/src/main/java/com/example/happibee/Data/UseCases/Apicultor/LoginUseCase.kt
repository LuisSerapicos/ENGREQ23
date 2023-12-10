package com.example.happibee.Data.UseCases.Apicultor

import com.example.happibee.Data.Repository.Apicultor.ApicultorRepositoryImpl
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: ApicultorRepositoryImpl) {
    operator fun invoke(name: String, password: String) = repository.login(name, password)
}