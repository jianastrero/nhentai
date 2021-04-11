package com.jianastrero.common.controller

import androidx.compose.runtime.Composable
import com.jianastrero.common.model.State
import com.jianastrero.common.state.CHECK_INTERNET_CONNECTION
import com.jianastrero.common.ui.ViewMangaDetails

val FETCH_MANGA_DETAILS = State("FETCH_MANGA_DETAILS") { fetchMangaDetails() }

val VIEW_MANGA_DETAILS = State("VIEW_MANGA_DETAILS") { ViewMangaDetails() }

val VIEW_MANGA_DETAILS_CONTROLLER = arrayOf(
    CHECK_INTERNET_CONNECTION,
    FETCH_MANGA_DETAILS,
    VIEW_MANGA_DETAILS
)

@Composable
fun fetchMangaDetails() {

}