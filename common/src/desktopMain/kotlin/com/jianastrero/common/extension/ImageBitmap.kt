package com.jianastrero.common.extension

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.skija.Image
import java.net.HttpURLConnection
import java.net.URL

actual suspend fun String.fetchImageBitmap(): ImageBitmap? = withContext(Dispatchers.IO) {
    var connection: HttpURLConnection? = null
    try {
        connection = URL(this@fetchImageBitmap).openConnection() as HttpURLConnection
        Image.makeFromEncoded(connection.inputStream.readBytes()).asImageBitmap()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    } finally {
        connection?.disconnect()
    }
}