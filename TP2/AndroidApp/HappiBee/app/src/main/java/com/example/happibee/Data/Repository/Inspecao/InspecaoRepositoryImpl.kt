package com.example.happibee.Data.Repository.Inspecao

import com.example.happibee.Data.Model.Apiario
import com.example.happibee.Data.Model.Inspecao
import com.example.happibee.Data.Repository.Apiario.ApiarioRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InspecaoRepositoryImpl @Inject constructor (private val dao: InspecaoRepository): InspecaoRepository {
    override suspend fun insertInspecao(inspecao: Inspecao) = dao.insertInspecao(inspecao = inspecao)

    override suspend fun updateInspecao(inspecao: Inspecao) = dao.updateInspecao(inspecao = inspecao)

    override fun getInspecoes(): Flow<List<Inspecao>> = dao.getInspecoes()

    override suspend fun getByIdInspecao(id: Int): Inspecao = dao.getByIdInspecao(id)
}