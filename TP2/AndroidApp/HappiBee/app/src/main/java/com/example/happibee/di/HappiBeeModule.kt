package com.example.happibee.di

import android.content.Context
import androidx.room.Room
import com.example.happibee.Data.Database.HappiBeeDatabase
import com.example.happibee.Data.Repository.Apiario.ApiarioRepositoryImpl
import com.example.happibee.Data.UseCases.Apiario.ApiarioUseCase
import com.example.happibee.Data.UseCases.Apiario.DeleteApiario
import com.example.happibee.Data.UseCases.Apiario.GetApiarios
import com.example.happibee.Data.UseCases.Apiario.GetByIdApiario
import com.example.happibee.Data.UseCases.Apiario.InsertApiario
import com.example.happibee.Data.UseCases.Apiario.UpdateApiario
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
    ).build()

    @Provides
    fun provideDao(dao: HappiBeeDatabase) = dao.apiarioDao()

    @Provides
    fun provideApiarioUseCase(repository: ApiarioRepositoryImpl) = ApiarioUseCase(
        getApiarios = GetApiarios(repository),
        insertApiario = InsertApiario(repository),
        deleteApiario = DeleteApiario(repository),
        updateApiario = UpdateApiario(repository),
        getByIdApiario = GetByIdApiario(repository)
    )
}