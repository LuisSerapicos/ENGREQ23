package com.example.happibee.di

import android.app.Application
import androidx.room.Room
import com.example.happibee.Data.Database.HappiBeeDatabase
import com.example.happibee.Data.Model.Apiario
import com.example.happibee.Data.Repository.ApiarioRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideHappiBeeDatabase(app: Application): HappiBeeDatabase {
        return Room.databaseBuilder(
            app,
            HappiBeeDatabase::class.java,
            "HappiBeeDB",
        ).build()
    }

    @Provides
    @Singleton
    fun provideApiarioRepository(db: HappiBeeDatabase): ApiarioRepository {
        return ApiarioRepository(db.apiarioDao())
    }
}