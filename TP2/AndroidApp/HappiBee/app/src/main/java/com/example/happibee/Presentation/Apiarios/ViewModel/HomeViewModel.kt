package com.example.happibee.Presentation.Apiarios.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happibee.Data.Model.Apiario
import com.example.happibee.Data.UseCases.Apiario.ApiarioUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val apiarioCase: ApiarioUseCase):ViewModel() {
    val apiarios=apiarioCase.getApiarios()

    fun deleteNote(apiario: Apiario)=viewModelScope.launch {
        apiarioCase.deleteApiario(apiario)
    }
}