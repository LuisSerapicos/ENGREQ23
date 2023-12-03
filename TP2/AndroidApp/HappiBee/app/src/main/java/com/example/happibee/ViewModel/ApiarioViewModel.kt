package com.example.happibee.ViewModel

import androidx.lifecycle.ViewModel
import com.example.happibee.Data.Repository.ApiarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ApiarioViewModel @Inject constructor(
    apiarioRepository: ApiarioRepository
): ViewModel() {
    val readAll = apiarioRepository.getAllApiarios()
}