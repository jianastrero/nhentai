package com.jianastrero.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
        Column {
            Breadcrumb(document, onTitleChange)
            if (document?.title() == HOME_TITLE) {
                Home()
            }
        }
    }
}

@Composable
fun Home() {
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

    if (document?.title() == HOME_TITLE || document?.title() == null) {
        onTitleChange("NHentai > Home")
    } else {
        val title = document.body()
            .getElementsByClass("title").first()
            .getElementsByClass("pretty").first()
            .html()
        text = "NHentai"
        onTitleChange("NHentai > $title")
    }

    Row(
        modifier = Modifier
            .padding(8.dp)
    ) {

        Text(text)
    }
}