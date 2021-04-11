package com.jianastrero.common.viewmodel

import androidx.compose.runtime.*
import com.jianastrero.common.controller.SERVER_UPDATED_ERROR_CONTROLLER
import com.jianastrero.common.extension.getFirstElementByClass
import com.jianastrero.common.extension.getFirstElementByTag
import com.jianastrero.common.model.Manga
import com.jianastrero.common.model.Tag
import com.jianastrero.common.model.TagList
import com.jianastrero.common.statemachine.StateMachine
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class ViewMangaDetailsViewModel {

    lateinit var manga: Manga

    val tags = mutableListOf<TagList>()

    var pages = 0

    @Composable
    fun fetch() {
        if (!::manga.isInitialized) {
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
                tags.clear()
                pages = 0

                GlobalScope.launch {
                    try {
                        val document = Jsoup.connect(manga.url()).get()

                    println(document.html())

                        val _tags = document
                            .getElementById("tags")
                            .getElementsByClass("tag-container")

                        _tags.forEach {
                            var title: String
                            val list = mutableListOf<Tag>()
                            it.also {
                                title = it.html().subSequence(0, it.html().indexOf(':')).trim().toString()
                                println("title: $title")
                            }.getFirstElementByClass("tags")
                                .getElementsByTag("a")
                                .forEach {
                                    it.getFirstElementByTag("a")?.let { aTag ->
                                        val urlSlug = aTag.attr("href")
                                        val _title = aTag.getFirstElementByClass("name").html()

                                        if (title == "Pages") {
                                            pages = _title.toInt()
                                        } else {
                                            val count = aTag.getFirstElementByClass("count").html()

                                            list.add(Tag(_title, urlSlug, count))
                                        }
                                    }
                                }
                            tags.add(TagList(title, list))
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