package com.jianastrero.common.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jianastrero.common.enumeration.AppStatus
import com.jianastrero.common.enumeration.Platform
import com.jianastrero.common.model.Manga
import com.jianastrero.common.platform.getPlatform
import com.jianastrero.common.view.Header
import com.jianastrero.common.view.MangaItem
import com.jianastrero.common.viewmodel.HomeViewModel
import org.jsoup.nodes.Document
import kotlin.math.ceil

internal val homeViewModel = HomeViewModel()

@Composable
fun Home(block: (AppStatus, Document?) -> Unit) {

    homeViewModel.fetch(block)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        PopularManga(homeViewModel.popularMangas)
        Spacer(modifier = Modifier.height(32.dp))
        AllManga(homeViewModel.allManga)
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
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp, 0.dp)
        ) {
            items(popularMangas) { MangaItem(it) }
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
            allManga.forEach { MangaItem(it, true) }
        }
    }
}

@Composable
fun StaggeredVerticalGrid(
    modifier: Modifier = Modifier,
    maxColumnWidth: Dp,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        check(constraints.hasBoundedWidth) {
            "Unbounded width not supported"
        }
        val columns = ceil(constraints.maxWidth / maxColumnWidth.toPx()).toInt()
        val columnWidth = constraints.maxWidth / columns
        val itemConstraints = constraints.copy(maxWidth = columnWidth)
        val colHeights = IntArray(columns) { 0 } // track each column's height
        val placeables = measurables.map { measurable ->
            val column = shortestColumn(colHeights)
            val placeable = measurable.measure(itemConstraints)
            colHeights[column] += placeable.height
            placeable
        }

        val height = colHeights.maxOrNull()?.coerceIn(constraints.minHeight, constraints.maxHeight)
            ?: constraints.minHeight
        layout(
            width = constraints.maxWidth,
            height = height
        ) {
            val colY = IntArray(columns) { 0 }
            placeables.forEach { placeable ->
                val column = shortestColumn(colY)
                placeable.place(
                    x = columnWidth * column,
                    y = colY[column]
                )
                colY[column] += placeable.height
            }
        }
    }
}

private fun shortestColumn(colHeights: IntArray): Int {
    var minHeight = Int.MAX_VALUE
    var column = 0
    colHeights.forEachIndexed { index, height ->
        if (height < minHeight) {
            minHeight = height
            column = index
        }
    }
    return column
}