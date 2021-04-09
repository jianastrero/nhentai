package com.jianastrero.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

private const val HOME_TITLE = "nhentai: hentai doujinshi and manga"
private const val HOME_URL = "https://nhentai.net/"

@Composable
fun App(onTitleChange: (String) -> Unit) {

    var document by remember { mutableStateOf<Document?>(null) }
    GlobalScope.launch {
        try {
            document = Jsoup.connect(HOME_URL).get()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    NHentaiTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Breadcrumb(document, onTitleChange)
            document?.let { document ->
                if (document.title() == HOME_TITLE) {
                    Home(document)
                }
            } ?: NoInternetConnection()
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
fun Home(document: Document) {
    Column {
        Text("Home")
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
        }
        else -> {
            val title = document.body()
                .getElementsByClass("title").first()
                .getElementsByClass("pretty").first()
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