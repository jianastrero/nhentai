package com.jianastrero.common.viewmodel

import com.jianastrero.HOME_URL
import com.jianastrero.common.database.MangaMapDatabase
import com.jianastrero.common.enumeration.AppStatus
import com.jianastrero.common.extension.getFirstElementByClass
import com.jianastrero.common.extension.getFirstElementByTag
import com.jianastrero.common.model.Manga
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class HomeViewModel {

    private val SHOULD_REFETCH_COOLDOWN = 5 * 1000

    private var lastFetch = 0L

    var popularMangas = mutableListOf<Manga>()
    var allManga = listOf<Manga>()

    fun fetch(block: (AppStatus, Document?) -> Unit) = GlobalScope.launch {
        if (System.currentTimeMillis() - lastFetch > SHOULD_REFETCH_COOLDOWN) {
            try {
                val document = Jsoup.connect(HOME_URL).get()

                val popularNow = document
                    .select(".index-container")
                    .select(".index-popular")
                    .first()

                popularNow?.getElementsByClass("gallery")?.forEach {
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

                block(AppStatus.LOADED, document)
                lastFetch = System.currentTimeMillis()
            } catch (e: Exception) {
                block(AppStatus.NO_INTERNET_CONNECTION, null)
                e.printStackTrace()
            }
        }
    }
}