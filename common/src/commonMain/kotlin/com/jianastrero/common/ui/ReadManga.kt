package com.jianastrero.common.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jianastrero.common.controller.HOME_CONTROLLER
import com.jianastrero.common.extension.mandatoryDelay
import com.jianastrero.common.statemachine.StateMachine
import com.jianastrero.common.viewmodel.ReadMangaViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

val readMangaViewModel = ReadMangaViewModel()

@Composable
fun ReadManga() {

    var page by remember { mutableStateOf(0) }
    var percentDownload by remember { mutableStateOf(0f) }
    var isBackClicked by remember { mutableStateOf(false) }

    GlobalScope.launch {
        mandatoryDelay()
        while(true) {
            val currentPercentDownload = readMangaViewModel.cachedImages.keys.size.toFloat() / readMangaViewModel.pages.toFloat()
            if (percentDownload != currentPercentDownload) {
                percentDownload = currentPercentDownload
            }
            if (page == 0 && readMangaViewModel.cachedImages.containsKey(1)) {
                page = 1
            }
            if (page > 0 && !readMangaViewModel.cachedImages.containsKey(page)) {
                while (!readMangaViewModel.cachedImages.containsKey(page)) {
                    delay(200)
                }
                page = page // Reset to update
            }
            delay(200)
        }
    }

    if (isBackClicked) {
        StateMachine.start(HOME_CONTROLLER)
    }

    Column {
        TopAppBar(
            title = {
                Text(
                    "NHentai > ${viewMangaDetailsViewModel.manga.title}",
                    maxLines = 1
                )
            },
            navigationIcon = {
                Icon(
                    Icons.Default.ArrowBack,
                    null,
                    modifier = Modifier
                        .clickable {
                            isBackClicked = true
                        }
                )
            },
            backgroundColor = MaterialTheme.colors.primary,
            elevation = 8.dp
        )
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            if (page != 0 && readMangaViewModel.cachedImages.containsKey(page)) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                ) {
                    Image(
                        readMangaViewModel.cachedImages[page]!!,
                        null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth()
                    )
                    Spacer(
                        modifier = Modifier
                            .height(64.dp)
                    )
                }
            } else {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
            Text(
                "$page/${readMangaViewModel.pages}",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .background(Color(0, 0, 0, 150))
                    .padding(8.dp)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    Button(
                        onClick = {
                            if (page > 1) {
                                page--
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text("Previous")
                    }
                    Button(
                        onClick = {
                            if (page < readMangaViewModel.pages) {
                                page++
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text("Next")
                    }
                }
                if (percentDownload < 1f) {
                    LinearProgressIndicator(
                        progress = percentDownload,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}