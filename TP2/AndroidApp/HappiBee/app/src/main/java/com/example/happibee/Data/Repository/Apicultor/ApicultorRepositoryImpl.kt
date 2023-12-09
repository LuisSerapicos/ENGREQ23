package com.example.happibee.Data.Repository.Apicultor

import com.example.happibee.Data.Model.Apiario
import com.example.happibee.Data.Model.Apicultor
import com.example.happibee.Data.Model.ApicultorWithApiarios
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApicultorRepositoryImpl @Inject constructor(private val dao:ApicultorRepository): ApicultorRepository {
    override suspend fun insertApicultor(apicultor: Apicultor) = dao.insertApicultor(apicultor)
    override suspend fun updateApicultor(apicultor: Apicultor) = dao.updateApicultor(apicultor)
    override fun getApicultors(): Flow<List<Apicultor>> = dao.getApicultors()
    override suspend fun getByIdApicultor(id: Int): Apicultor = dao.getByIdApicultor(id)
    override suspend fun getApicultorWithApiarios(id: Int): ApicultorWithApiarios = dao.getApicultorWithApiarios(id)
    override fun login(name: String, password: String): Apicultor? = dao.login(name, password)
}