package es.niadecode.common.scene

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import es.niadecode.common.viewmodel.GameState
import es.niadecode.common.viewmodel.MainSceneViewModel
import moe.tlaster.precompose.viewmodel.viewModel


@Composable
fun MainScene() {
    val viewModel = viewModel(modelClass = MainSceneViewModel::class, keys = listOf(null)) {
        MainSceneViewModel()
    }

    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("HIGH SCORE ${state.maxScore}\n${state.lastUserNameMVP}")

        Text(state.currentScore.toString())

        val shameText = if (state is GameState.GameOver) {
            "Shame on ${state.lastUserName}"
        } else {
            state.lastUserName
        }

        Text(shameText)
    }

}
