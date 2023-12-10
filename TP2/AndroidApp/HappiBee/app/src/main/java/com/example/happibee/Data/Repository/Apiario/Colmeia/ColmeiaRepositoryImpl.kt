package com.example.happibee.Data.Repository.Apiario.Colmeia

import com.example.happibee.Data.Model.Apiario
import com.example.happibee.Data.Model.Colmeia
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ColmeiaRepositoryImpl @Inject constructor (private val dao: ColmeiaRepository): ColmeiaRepository {
    override suspend fun insertColmeia(colmeia: Colmeia) = dao.insertColmeia(colmeia = colmeia)

    override suspend fun updateColmeia(colmeia: Colmeia) = dao.updateColmeia(colmeia = colmeia)
    override suspend fun deleteColmeia(colmeia: Colmeia) = dao.deleteColmeia(colmeia = colmeia)

    override fun getColmeia(): Flow<List<Colmeia>> = dao.getColmeia()

    override suspend fun getByIdColmeia(id: Int): Colmeia = dao.getByIdColmeia(id)
    override fun getByApiarioId(id: Int): Flow<List<Colmeia>> = dao.getByApiarioId(id)

}