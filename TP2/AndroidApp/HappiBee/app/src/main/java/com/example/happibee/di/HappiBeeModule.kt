package com.example.happibee.di

import android.content.Context
import androidx.room.Room
import com.example.happibee.Data.Database.HappiBeeDatabase
import com.example.happibee.Data.Repository.Apiario.ApiarioRepositoryImpl
import com.example.happibee.Data.Repository.Apiario.Colmeia.ColmeiaRepositoryImpl
import com.example.happibee.Data.Repository.Apicultor.ApicultorRepositoryImpl
import com.example.happibee.Data.Repository.Inspecao.InspecaoRepositoryImpl
import com.example.happibee.Data.UseCases.Apiario.ApiarioUseCase
import com.example.happibee.Data.UseCases.Apiario.DeleteApiario
import com.example.happibee.Data.UseCases.Apiario.GetApiarios
import com.example.happibee.Data.UseCases.Apiario.GetByApicultorId
import com.example.happibee.Data.UseCases.Apiario.GetByIdApiario
import com.example.happibee.Data.UseCases.Apiario.InsertApiario
import com.example.happibee.Data.UseCases.Apiario.UpdateApiario
import com.example.happibee.Data.UseCases.Apicultor.ApicultorUseCase
import com.example.happibee.Data.UseCases.Apicultor.GetApiariosByIdApicultor
import com.example.happibee.Data.UseCases.Apicultor.GetApicultors
import com.example.happibee.Data.UseCases.Apicultor.GetByIdApicultor
import com.example.happibee.Data.UseCases.Apicultor.InsertApicultor
import com.example.happibee.Data.UseCases.Apicultor.LoginUseCase
import com.example.happibee.Data.UseCases.Apicultor.UpdateApicultor
import com.example.happibee.Data.UseCases.Colmeia.ColmeiaUseCase
import com.example.happibee.Data.UseCases.Colmeia.DeleteColmeia
import com.example.happibee.Data.UseCases.Colmeia.GetColmeias
import com.example.happibee.Data.UseCases.Colmeia.InsertColmeia
import com.example.happibee.Data.UseCases.Colmeia.UpdateColmeia
import com.example.happibee.Data.UseCases.Inspecao.GetByIdInspecao
import com.example.happibee.Data.UseCases.Inspecao.GetInspecaoByApiario
import com.example.happibee.Data.UseCases.Inspecao.GetInspecoes
import com.example.happibee.Data.UseCases.Inspecao.InsertInspecao
import com.example.happibee.Data.UseCases.Inspecao.InspecaoUseCase
import com.example.happibee.Data.UseCases.Inspecao.UpdateInspecao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.http.GET

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
    fun provideColmeiaDao(dao: HappiBeeDatabase) = dao.colmeiaDao()
    @Provides
    fun provideApicultorDao(dao: HappiBeeDatabase) = dao.apicultorDao()
    @Provides
    fun provideInspecaoDao(dao: HappiBeeDatabase) = dao.inspecaoDao()

    @Provides
    fun provideApiarioUseCase(repository: ApiarioRepositoryImpl) = ApiarioUseCase(
        getApiarios = GetApiarios(repository),
        insertApiario = InsertApiario(repository),
        deleteApiario = DeleteApiario(repository),
        updateApiario = UpdateApiario(repository),
        getByIdApiario = GetByIdApiario(repository),
        getByApicultorId = GetByApicultorId(repository)
    )

    @Provides
    fun provideColmeiaUseCase(repository: ColmeiaRepositoryImpl) = ColmeiaUseCase(
        getColmeias = GetColmeias(repository),
        insertColmeia = InsertColmeia(repository),
        deleteColmeia = DeleteColmeia(repository),
        updateColmeia = UpdateColmeia(repository),
        getByIdApiario = com.example.happibee.Data.UseCases.Colmeia.GetByIdApiario(repository),
    )

    @Provides
    fun provideApicultorUseCase(repository: ApicultorRepositoryImpl) = ApicultorUseCase(
        getApicultors = GetApicultors(repository),
        insertApicultor = InsertApicultor(repository),
        updateApicultor = UpdateApicultor(repository),
        getByIdApicultor = GetByIdApicultor(repository),
        getApiariosByIdApicultor = GetApiariosByIdApicultor(repository),
        login = LoginUseCase(repository)
    )

    @Provides
    fun provideInspecaoUseCase(repository: InspecaoRepositoryImpl) = InspecaoUseCase(
        getInspecoes = GetInspecoes(repository),
        insertInspecao = InsertInspecao(repository),
        updateInspecao = UpdateInspecao(repository),
        getByIdInspecao = GetByIdInspecao(repository),
        getInspecaoByApiario = GetInspecaoByApiario(repository)
    )
}