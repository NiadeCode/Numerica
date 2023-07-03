package com.example.common

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

@Composable
fun App() {

    val viewModel = viewModel(modelClass = SomeViewModel::class, keys = listOf(null)) {
        SomeViewModel()
    }

    val text by viewModel.text.collectAsState()

    Button(onClick = {
        viewModel.plusOne()
    }) {
        Text(text.toString())
    }
}


class SomeViewModel : ViewModel() {

    private val _text by lazy {
        MutableStateFlow<Int>(1)
    }

    val text: StateFlow<Int> = _text

    fun plusOne() {
        viewModelScope.launch {
            _text.value = _text.value+1
        }
    }


}




