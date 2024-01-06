package com.example.happibee

import android.app.Application
import com.example.happibee.Data.Database.DatabaseBootstrapper
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class HappiBeeApp:Application() {
    @Inject lateinit var databaseBootstrapper: DatabaseBootstrapper

    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO).launch {
            databaseBootstrapper.initializeData()
        }
    }
}