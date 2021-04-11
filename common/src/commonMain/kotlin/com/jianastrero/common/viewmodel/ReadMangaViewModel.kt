package com.jianastrero.common.viewmodel

import androidx.compose.runtime.Composable
import com.jianastrero.common.model.Manga
import com.jianastrero.common.statemachine.StateMachine

class ReadMangaViewModel {

    lateinit var manga: Manga
    var pages: Int = 0

    @Composable
    fun fetch() {
        StateMachine.nextState()
    }
}