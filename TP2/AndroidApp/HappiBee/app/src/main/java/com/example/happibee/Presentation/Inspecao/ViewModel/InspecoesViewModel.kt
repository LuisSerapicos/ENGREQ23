package com.example.happibee.Presentation.Inspecao.ViewModel

import androidx.lifecycle.ViewModel
import com.example.happibee.Data.UseCases.Inspecao.InspecaoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InspecoesViewModel @Inject constructor(private val inspecaoUseCase: InspecaoUseCase): ViewModel() {
    val inspecoes = inspecaoUseCase.getInspecoes()
}