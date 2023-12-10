package com.example.happibee.Presentation.Inspecao.ViewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.happibee.Data.UseCases.Inspecao.InspecaoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InspecoesViewModel @Inject constructor(
    private val inspecaoUseCase: InspecaoUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    val inspecoes = inspecaoUseCase.getInspecoes()

    var id=savedStateHandle.get<Int>(key = "id")
    val inspecoesApiario = inspecaoUseCase.getInspecaoByApiario(id!!)
}