package com.jianastrero.common.viewmodel

import androidx.compose.runtime.*
import com.jianastrero.HOME_URL
import com.jianastrero.common.controller.SERVER_UPDATED_ERROR_CONTROLLER
import com.jianastrero.common.database.MangaMapDatabase
import com.jianastrero.common.extension.getFirstElementByClass
import com.jianastrero.common.extension.getFirstElementByTag
import com.jianastrero.common.model.Manga
import com.jianastrero.common.statemachine.StateMachine
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class HomeViewModel {

    var popularMangas = mutableListOf<Manga>()
    var allManga = mutableListOf<Manga>()

    @Composable
    fun fetch() {
        var state by remember { mutableStateOf(-1) }

        when (state) {
            0 -> {
                StateMachine.finish()
                StateMachine.start(SERVER_UPDATED_ERROR_CONTROLLER)
            }
            1 -> StateMachine.nextState()
            else -> {
                GlobalScope.launch {
                    try {
                        val document = Jsoup.connect(HOME_URL).get()

                        val popularNow = document
                            .select(".index-container")
                            .select(".index-popular")
                            .first()

                        popularNow.getElementsByClass("gallery").forEach {
                            val id = it.getFirstElementByTag("a").attr("href").split("/")[2]
                            val img = it.getFirstElementByTag("img")
                                .attr("data-src")
                                .replace("http://", "")
                                .replace("https://", "")
                                .split("/")
                            val galleryId = img[2]
                            val thumbnailExtension = img[3].split('.')[1]
                            val title = it.getFirstElementByClass("caption").html()

                            val manga = Manga(id, galleryId, title, thumbnailExtension)
                            MangaMapDatabase.insert(manga)
                            popularMangas.add(manga)
                        }

                        popularNow.remove()

                        document.getElementsByClass("gallery").forEach {
                            val id = it.getFirstElementByTag("a").attr("href").split("/")[2]
                            val img = it.getFirstElementByTag("img")
                                .attr("data-src")
                                .replace("http://", "")
                                .replace("https://", "")
                                .split("/")
                            val galleryId = img[2]
                            val thumbnailExtension = img[3].split('.')[1]
                            val title = it.getFirstElementByClass("caption").html()

                            val manga = Manga(id, galleryId, title, thumbnailExtension)
                            MangaMapDatabase.insert(manga)
                            allManga.add(manga)
                        }
                        state = 1
                    } catch (e: Exception) {
                        e.printStackTrace()
                        state = 0
                    }
                }
            }
        }
    }
}