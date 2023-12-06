package com.example.happibee.di

import android.content.Context
import androidx.room.Room
import com.example.happibee.Data.Database.HappiBeeDatabase
import com.example.happibee.Data.Repository.Apiario.ApiarioRepositoryImpl
import com.example.happibee.Data.Repository.Apicultor.ApicultorRepositoryImpl
import com.example.happibee.Data.UseCases.Apiario.ApiarioUseCase
import com.example.happibee.Data.UseCases.Apiario.DeleteApiario
import com.example.happibee.Data.UseCases.Apiario.GetApiarios
import com.example.happibee.Data.UseCases.Apiario.GetByIdApiario
import com.example.happibee.Data.UseCases.Apiario.InsertApiario
import com.example.happibee.Data.UseCases.Apiario.UpdateApiario
import com.example.happibee.Data.UseCases.Apicultor.ApicultorUseCase
import com.example.happibee.Data.UseCases.Apicultor.GetApiariosByIdApicultor
import com.example.happibee.Data.UseCases.Apicultor.GetApicultors
import com.example.happibee.Data.UseCases.Apicultor.GetByIdApicultor
import com.example.happibee.Data.UseCases.Apicultor.InsertApicultor
import com.example.happibee.Data.UseCases.Apicultor.UpdateApicultor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HappiBeeModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): HappiBeeDatabase = Room.databaseBuilder(
        context = context,
        klass = HappiBeeDatabase::class.java,
        name = "HappiBeeDB"
    ).fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideApiarioDao(dao: HappiBeeDatabase) = dao.apiarioDao()
    @Provides
    fun provideApicultorDao(dao: HappiBeeDatabase) = dao.apicultorDao()

    @Provides
    fun provideApiarioUseCase(repository: ApiarioRepositoryImpl) = ApiarioUseCase(
        getApiarios = GetApiarios(repository),
        insertApiario = InsertApiario(repository),
        deleteApiario = DeleteApiario(repository),
        updateApiario = UpdateApiario(repository),
        getByIdApiario = GetByIdApiario(repository)
    )

    @Provides
    fun provideApicultorUseCase(repository: ApicultorRepositoryImpl) = ApicultorUseCase(
        getApicultors = GetApicultors(repository),
        insertApicultor = InsertApicultor(repository),
        updateApicultor = UpdateApicultor(repository),
        getByIdApicultor = GetByIdApicultor(repository),
        getApiariosByIdApicultor = GetApiariosByIdApicultor(repository)
    )
}