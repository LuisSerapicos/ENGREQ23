package com.example.happibee.Presentation.Desdobramento.ViewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.happibee.Data.UseCases.Colmeia.ColmeiaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DesdobramentosViewModel @Inject constructor(
    private val colmeiaUseCase: ColmeiaUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    val colmeias = colmeiaUseCase.getColmeias()

    var id=savedStateHandle.get<Int>(key = "id")
    val colmeiasApiario = colmeiaUseCase.getByIdApiario(id!!)
}