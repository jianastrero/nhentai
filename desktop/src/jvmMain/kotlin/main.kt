
import androidx.compose.desktop.LocalAppWindow
import androidx.compose.desktop.Window
import com.jianastrero.common.platform.getAppName
import com.jianastrero.common.ui.App

fun main(args: Array<String>) {
    Window(getAppName()) {
        System.setProperty("https.protocols", "TLSv1.1,TLSv1.2")
        val window = LocalAppWindow.current

        App { window.setTitle(it) }
    }
}