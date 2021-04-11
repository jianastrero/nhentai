package com.jianastrero.common.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import com.jianastrero.common.model.Manga
import com.jianastrero.common.repository.ImageRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ReadMangaViewModel {

    lateinit var manga: Manga
    var pages: Int = 0

    val cachedImages = mutableMapOf<Int, ImageBitmap>()

    @Composable
    fun fetch() {
        if (!::manga.isInitialized || pages == 0) {
            return
        }


        GlobalScope.launch {
            cachedImages.clear()
            try {
                println("pages: $pages")

                repeat(pages) {
                    println("fetching page#${it+1}")
                    ImageRepository.fetchImage(manga.imageUrl(it + 1))?.let { image ->
                        cachedImages[it+1] = image
                    }
                }

                println("fetched images: ${cachedImages.size}")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}