package com.jianastrero.common.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jianastrero.common.enumeration.AppStatus
import com.jianastrero.common.enumeration.Platform
import com.jianastrero.common.model.Manga
import com.jianastrero.common.platform.getPlatform
import com.jianastrero.common.view.Header
import com.jianastrero.common.view.MangaItem
import com.jianastrero.common.view.StaggeredVerticalGrid
import com.jianastrero.common.viewmodel.HomeViewModel
import org.jsoup.nodes.Document

internal val homeViewModel = HomeViewModel()

@Composable
fun Home(block: (AppStatus, Document?) -> Unit, onItemClick: (Manga) -> Unit) {

    homeViewModel.fetch(block)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        PopularManga(homeViewModel.popularMangas, onItemClick)
        Spacer(modifier = Modifier.height(32.dp))
        AllManga(homeViewModel.allManga, onItemClick)
    }
}

@Composable
fun PopularManga(popularMangas: List<Manga>, onItemClick: (Manga) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Header("Popular Now")
        LazyRow(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp, 0.dp)
        ) {
            items(popularMangas) {
                MangaItem(it) {
                    onItemClick(it)
                }
            }
        }
    }
}

@Composable
fun AllManga(allManga: List<Manga>, onItemClick: (Manga) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Header("All Manga (Recent Upload First)")
        StaggeredVerticalGrid(
            maxColumnWidth = when (getPlatform()) {
                Platform.ANDROID -> 640.dp
                Platform.DESKTOP -> 240.dp
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp, 0.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            allManga.forEach {
                MangaItem(it, true, 0) {
                    onItemClick(it)
                }
            }
        }
    }
}
