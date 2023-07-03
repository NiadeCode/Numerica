package es.niadecode.common

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

@Composable
fun App() {

    val navigator = rememberNavigator()
    MaterialTheme {
        NavHost(
            navigator = navigator,
            initialRoute = "/home"
        ) {
            scene("/home") {
                val viewModel = viewModel(modelClass = SomeViewModel::class, keys = listOf(null)) {
                    SomeViewModel()
                }

                val text by viewModel.text.collectAsState()

                Row {
                    Button(onClick = {
                        viewModel.plusOne()
                    }) {
                        Text(text.toString())
                    }

                    Button(onClick = {
                        navigator.navigate("/detail/1")
                    }) {
                        Text("navega")
                    }
                }
            }

            scene("/detail/{id:[0-9]+}") { backStackEntry ->
                backStackEntry.path<Int>("id")?.let{

                }
            }
        }


    }
}


class SomeViewModel : ViewModel() {

    private val _text by lazy {
        MutableStateFlow<Int>(1)
    }

    val text: StateFlow<Int> = _text

    fun plusOne() {
        viewModelScope.launch {
            _text.value = _text.value + 1
        }
    }


}




