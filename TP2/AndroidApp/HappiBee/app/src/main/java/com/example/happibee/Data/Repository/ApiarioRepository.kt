package com.example.happibee.Data.Repository

import com.example.happibee.Data.Dao.ApiarioDao
import com.example.happibee.Data.Model.Apiario
import kotlinx.coroutines.flow.Flow

class ApiarioRepository(
    private val dao: ApiarioDao
) {
    suspend fun insertApiario(apiario: Apiario) {
        dao.insert(apiario)
    }

    suspend fun deleteApiario(apiario: Apiario) {
        dao.delete(apiario)
    }

    suspend fun getApiarioById(name: String): Apiario? {
        return dao.findByName(name)
    }

    fun getAllApiarios(): Flow<List<Apiario>> {
        return dao.getAll()
    }
}