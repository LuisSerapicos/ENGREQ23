package com.example.happibee.Presentation.Apiarios.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happibee.Data.Model.Apiario
import com.example.happibee.Data.PreferencesDataStore.DataStoreManager
import com.example.happibee.Data.UseCases.Apiario.ApiarioUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiarioCase: ApiarioUseCase,
    @ApplicationContext private val context: Context // Inject the context
) : ViewModel() {

    val apiarios = apiarioCase.getApiarios()
    val dataStoreManager = DataStoreManager.getInstance(context)


    val filteredApiarios: Flow<List<Apiario>> = apiarios.map { apiariosList ->
        val apicultorId = dataStoreManager.getName()
        Log.d("CHECK data", apicultorId)
        apiariosList.filter { it.apicultorId == apicultorId.toInt() }
    }

    fun deleteNote(apiario: Apiario) = viewModelScope.launch {
        apiarioCase.deleteApiario(apiario)
    }
}