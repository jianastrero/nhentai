package com.jianastrero.common.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jianastrero.common.controller.VIEW_MANGA_DETAILS_CONTROLLER
import com.jianastrero.common.enumeration.Platform
import com.jianastrero.common.model.Manga
import com.jianastrero.common.platform.getAppName
import com.jianastrero.common.platform.getPlatform
import com.jianastrero.common.statemachine.StateMachine
import com.jianastrero.common.view.Header
import com.jianastrero.common.view.MangaItem
import com.jianastrero.common.view.StaggeredVerticalGrid
import com.jianastrero.common.viewmodel.HomeViewModel

internal val homeViewModel = HomeViewModel()

@Composable
fun Home() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        TopAppBar(
            title = {
                Text("Welcome to ${getAppName()}")
            },
            backgroundColor = MaterialTheme.colors.primary,
            elevation = 8.dp
        )
        PopularManga(homeViewModel.popularMangas)
        Spacer(modifier = Modifier.height(32.dp))
        AllManga(homeViewModel.allManga)
    }

    StateMachine.finish()
}

@Composable
fun PopularManga(popularMangas: List<Manga>) {
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
                    StateMachine.start(VIEW_MANGA_DETAILS_CONTROLLER)
                }
            }
        }
    }
}

@Composable
fun AllManga(allManga: List<Manga>) {
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
                    StateMachine.start(VIEW_MANGA_DETAILS_CONTROLLER)
                }
            }
        }
    }
}
