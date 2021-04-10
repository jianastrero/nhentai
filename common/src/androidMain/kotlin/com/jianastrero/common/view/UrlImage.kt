package com.jianastrero.common.view

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL

@Composable
actual fun UrlImage(url: String, roundCornerSize: Int) {

    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }

    GlobalScope.launch {
        try {
            val connection = URL(url).openConnection() as HttpURLConnection
            imageBitmap = BitmapFactory.decodeStream(connection.inputStream).asImageBitmap()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    if (imageBitmap != null) {
        Image(imageBitmap!!, null, modifier = Modifier.clip(RoundedCornerShape(roundCornerSize.dp)))
    }
}