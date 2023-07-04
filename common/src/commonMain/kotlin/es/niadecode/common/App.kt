package es.niadecode.common

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import es.niadecode.common.scene.MainScene
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator

@Composable
fun App() {

    val navigator = rememberNavigator()
    MaterialTheme {
        NavHost(
            navigator = navigator,
            initialRoute = "/home"
        ) {

            scene("/home") {
                MainScene { navigator.navigate("/detail/$it") }
            }

            scene("/detail/{id:[0-9]+}") { backStackEntry ->
                backStackEntry.path<Int>("id")?.let {

                }
            }
        }


    }
}
