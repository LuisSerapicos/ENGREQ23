package com.example.happibee.Data.Repository.Apiario

import com.example.happibee.Data.Model.Apiario
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApiarioRepositoryImpl @Inject constructor (private val dao: ApiarioRepository): ApiarioRepository {
    override suspend fun insertApiario(apiario: Apiario) = dao.insertApiario(apiario = apiario)

    override suspend fun updateApiario(apiario: Apiario) = dao.updateApiario(apiario = apiario)
    override suspend fun deleteApiario(apiario: Apiario) = dao.deleteApiario(apiario = apiario)

    override fun getApiarios(): Flow<List<Apiario>> = dao.getApiarios()

    override suspend fun getByIdApiario(id: Int): Apiario = dao.getByIdApiario(id)

}