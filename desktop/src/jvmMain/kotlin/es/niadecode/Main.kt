package es.niadecode

import androidx.compose.ui.window.application
import es.niadecode.common.App
import moe.tlaster.precompose.PreComposeWindow


fun main() {
    application {
        PreComposeWindow(onCloseRequest = ::exitApplication) {
            App()
        }
    }
}
