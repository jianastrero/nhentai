package com.jianastrero.common.repository

import androidx.compose.ui.graphics.ImageBitmap
import com.jianastrero.common.extension.fetchImageBitmap

object ImageRepository {

    private val cache = mutableMapOf<String, ImageBitmap>()

    suspend fun fetchImage(url: String): ImageBitmap? {
        return if (cache.containsKey(url)) {
            cache[url]
        } else {
            url.fetchImageBitmap()?.also {
                cache[url] = it
            }
        }
    }

}