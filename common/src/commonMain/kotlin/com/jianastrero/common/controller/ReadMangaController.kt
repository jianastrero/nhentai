package com.jianastrero.common.controller

import com.jianastrero.common.model.State
import com.jianastrero.common.state.CHECK_INTERNET_CONNECTION
import com.jianastrero.common.statemachine.StateMachine
import com.jianastrero.common.ui.ReadManga
import com.jianastrero.common.ui.readMangaViewModel

val READ_MANGA = State("READ_MANGA") {
    readMangaViewModel.fetch()
    ReadManga()
    StateMachine.finish()
}

val READ_MANGA_CONTROLLER = arrayOf(
    CHECK_INTERNET_CONNECTION,
    READ_MANGA
)