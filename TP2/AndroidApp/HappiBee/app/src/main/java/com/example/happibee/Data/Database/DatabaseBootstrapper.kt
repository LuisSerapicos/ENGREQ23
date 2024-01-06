package com.example.happibee.Data.Database

import com.example.happibee.Data.Model.Apicultor
import com.example.happibee.Data.Repository.Apicultor.ApicultorRepository
import javax.inject.Inject

class DatabaseBootstrapper @Inject constructor(
    private val apicultorDao: ApicultorRepository
) {
    suspend fun initializeData() {
        if (apicultorDao.getByIdApicultor(1) == null) {
            val apicultor1 = Apicultor(1, "Toni", "toni@example.com", "244214242", "toni123")
            apicultorDao.insertApicultor(apicultor1)
        }
        if (apicultorDao.getByIdApicultor(2) == null) {
            val apicultor2 = Apicultor(2, "Joao", "joao@example.com", "244214242", "joao123")
            apicultorDao.insertApicultor(apicultor2)
        }
        if (apicultorDao.getByIdApicultor(3) == null) {
            val apicultor3 = Apicultor(3, "Toze", "toze@example.com", "244214242", "toze123")
            apicultorDao.insertApicultor(apicultor3)
        }
        if (apicultorDao.getByIdApicultor(4) == null) {
            val apicultor4 = Apicultor(4, "Xico", "xico@example.com", "244214242", "xico123")
            apicultorDao.insertApicultor(apicultor4)
        }
    }
}