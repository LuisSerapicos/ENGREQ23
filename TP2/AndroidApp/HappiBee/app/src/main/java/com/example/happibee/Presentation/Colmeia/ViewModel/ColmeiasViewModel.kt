package com.example.happibee.Presentation.Colmeia.ViewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happibee.Data.Model.Apiario
import com.example.happibee.Data.Model.Colmeia
import com.example.happibee.Data.UseCases.Colmeia.ColmeiaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ColmeiasViewModel @Inject constructor(
    private val colmeiaUseCase: ColmeiaUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    val colmeias = colmeiaUseCase.getColmeias()

    var id=savedStateHandle.get<Int>(key = "id")
    val colmeiasApiario = colmeiaUseCase.getByIdApiario(id!!)

    fun deleteColmeia(colmeia: Colmeia) = viewModelScope.launch {
        colmeiaUseCase.deleteColmeia(colmeia)
    }
}