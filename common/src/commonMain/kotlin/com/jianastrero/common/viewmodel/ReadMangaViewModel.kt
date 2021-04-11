package com.jianastrero.common.viewmodel

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.ImageBitmap
import com.jianastrero.common.controller.SERVER_UPDATED_ERROR_CONTROLLER
import com.jianastrero.common.model.Manga
import com.jianastrero.common.repository.ImageRepository
import com.jianastrero.common.statemachine.StateMachine
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

        var state by remember { mutableStateOf(-1) }

        when (state) {
            0 -> {
                StateMachine.finish()
                StateMachine.start(SERVER_UPDATED_ERROR_CONTROLLER)
            }
            1 -> StateMachine.nextState()
            else -> {
                cachedImages.clear()

                GlobalScope.launch {
                    state = try {
                        println("pages: $pages")

                        repeat(pages) {
                            println("fetching page#${it+1}")
                            ImageRepository.fetchImage(manga.imageUrl(it + 1))?.let { image ->
                                cachedImages[it+1] = image
                            }
                        }

                        println("fetched images: ${cachedImages.size}")

                        1
                    } catch (e: Exception) {
                        e.printStackTrace()
                        0
                    }
                }
            }
        }
    }
}