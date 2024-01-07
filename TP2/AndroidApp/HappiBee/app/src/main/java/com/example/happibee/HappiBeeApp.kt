package com.example.happibee

import android.app.Application
import com.example.happibee.Data.Database.DatabaseBootstrapper
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
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

        val firestoreSettings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()

        FirebaseFirestore.getInstance().firestoreSettings = firestoreSettings
    }
}