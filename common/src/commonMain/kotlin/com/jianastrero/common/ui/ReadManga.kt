package com.jianastrero.common.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.jianastrero.common.viewmodel.ReadMangaViewModel

val readMangaViewModel = ReadMangaViewModel()

@Composable
fun ReadManga() {
    Column() {
        Text("Read Manga")
    }
}