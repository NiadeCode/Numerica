import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.common.App
import moe.tlaster.precompose.PreComposeWindow


fun main() = application {
    PreComposeWindow(onCloseRequest = ::exitApplication) {
        App()
    }
}
