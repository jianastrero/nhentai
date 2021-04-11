package com.jianastrero.common.controller

import androidx.compose.runtime.Composable
import com.jianastrero.common.model.State
import com.jianastrero.common.state.CHECK_INTERNET_CONNECTION
import com.jianastrero.common.statemachine.StateMachine
import com.jianastrero.common.ui.Loading
import com.jianastrero.common.ui.ReadManga
import com.jianastrero.common.ui.readMangaViewModel

val FETCH_READ_MANGA = State("FETCH_READ_MANGA") { fetchHome() }

val READ_MANGA = State("READ_MANGA") {
    ReadManga()
    StateMachine.finish()
}

val READ_MANGA_CONTROLLER = arrayOf(
    CHECK_INTERNET_CONNECTION,
    FETCH_READ_MANGA,
    READ_MANGA
)

@Composable
fun fetchManga() {
    Loading()
    readMangaViewModel.fetch()
}