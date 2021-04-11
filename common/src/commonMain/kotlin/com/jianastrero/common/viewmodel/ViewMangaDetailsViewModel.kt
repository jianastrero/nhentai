package com.jianastrero.common.viewmodel

import androidx.compose.runtime.*
import com.jianastrero.common.controller.SERVER_UPDATED_ERROR_CONTROLLER
import com.jianastrero.common.model.Manga
import com.jianastrero.common.statemachine.StateMachine
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class ViewMangaDetailsViewModel {

    lateinit var manga: Manga

    @Composable
    fun fetch() {
        if (!::manga.isInitialized) {
            return
        }

        var state by remember { mutableStateOf(-1) }

        GlobalScope.launch {
            try {
                val document = Jsoup.connect(manga.url()).get()

                println(document.html())
                state = 1
            } catch (e: Exception) {
                e.printStackTrace()
                state = 0
            }
            manga.url()
        }

        if (state == 0) {
            StateMachine.finish()
            StateMachine.start(SERVER_UPDATED_ERROR_CONTROLLER)
        } else if (state == 1) {
            StateMachine.nextState()
        }
    }

}