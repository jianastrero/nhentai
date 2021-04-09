
import androidx.compose.desktop.LocalAppWindow
import androidx.compose.desktop.Window
import com.jianastrero.common.ui.App

fun main(args: Array<String>) {
    Window {
        val window = LocalAppWindow.current

        App { window.setTitle(it) }
    }
}