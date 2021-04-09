package com.jianastrero.common.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.skija.Image
import java.net.HttpURLConnection
import java.net.URL

@Composable
actual fun UrlImage(url: String, width: Int) {

    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }

    GlobalScope.launch {
        try {
            val connection = URL(url).openConnection() as HttpURLConnection
            imageBitmap = Image.makeFromEncoded(connection.inputStream.readBytes()).asImageBitmap()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    if (imageBitmap != null) {
        Image(imageBitmap!!, null, modifier = Modifier.width(width.dp))
    }
}