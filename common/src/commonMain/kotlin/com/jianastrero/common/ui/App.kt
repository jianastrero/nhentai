package com.jianastrero.common.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jianastrero.common.enumeration.AppStatus
import com.jianastrero.common.extension.getFirstElementByClass
import com.jianastrero.common.theme.NHentaiTheme
import org.jsoup.nodes.Document

internal const val HOME_TITLE = "nhentai: hentai doujinshi and manga"
internal const val HOME_URL = "https://nhentai.net/"
internal const val GALLERY_URL = "https://t.nhentai.net/galleries/"

@Composable
fun App(onTitleChange: (String) -> Unit) {

    var status by remember { mutableStateOf(AppStatus.LOADING) }
    var appDocument by remember { mutableStateOf<Document?>(null) }

    homeViewModel.fetch { appStatus, document ->
        appDocument = document
        status = appStatus
    }

    NHentaiTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Breadcrumb(appDocument, onTitleChange)
            when (status) {
                AppStatus.LOADING -> Loading()
                AppStatus.LOADED -> Home { appStatus, document ->
                    appDocument = document
                    status = appStatus
                }
                AppStatus.NO_INTERNET_CONNECTION -> NoInternetConnection()
            }
        }
    }
}

@Composable
fun NoInternetConnection() {
    Box(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(
            "No Internet Connection",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Composable
fun Loading() {
    Box(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Composable
fun ViewMangaDetails() {
    Column {
        Text("View Manga Details")
    }
}

@Composable
fun ReadManga() {
    Column {
        Text("Read Manga")
    }
}

@Composable
fun Breadcrumb(document: Document?, onTitleChange: (String) -> Unit) {

    var text by remember { mutableStateOf("Welcome to NHentai") }

    when {
        document == null -> {
            onTitleChange("NHentai :: Check your internet connection")
            text = ""
        }
        document.title() == HOME_TITLE -> {
            onTitleChange("NHentai > Home")
            text = "Welcome to NHentai"
        }
        else -> {
            val title = document.body()
                .getFirstElementByClass("title")
                .getFirstElementByClass("pretty")
                .html()
            text = "NHentai"
            onTitleChange("NHentai > $title")
        }
    }

    Row(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Text(text)
    }
}