package com.jianastrero.common.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jianastrero.common.enumeration.AppStatus
import com.jianastrero.common.model.Manga
import com.jianastrero.common.view.Header
import com.jianastrero.common.view.MangaItem
import com.jianastrero.common.viewmodel.HomeViewModel
import org.jsoup.nodes.Document

internal val homeViewModel = HomeViewModel()

@Composable
fun Home(block: (AppStatus, Document?) -> Unit) {

    homeViewModel.fetch(block)

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        PopularManga(homeViewModel.popularMangas)
    }
}

@Composable
fun PopularManga(popularMangas: List<Manga>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Header("Popular Now")
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp, 0.dp)
        ) {
            items(popularMangas) { MangaItem(it) }
        }
    }
}