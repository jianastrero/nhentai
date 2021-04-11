package com.jianastrero.common.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jianastrero.common.color.Red300
import com.jianastrero.common.controller.HOME_CONTROLLER
import com.jianastrero.common.extension.mandatoryDelay
import com.jianastrero.common.model.MySnackbarData
import com.jianastrero.common.model.Tag
import com.jianastrero.common.repository.ImageRepository
import com.jianastrero.common.statemachine.StateMachine
import com.jianastrero.common.view.Header
import com.jianastrero.common.view.ShowSnackbar
import com.jianastrero.common.view.StaggeredVerticalGrid
import com.jianastrero.common.viewmodel.ViewMangaDetailsViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal val viewMangaDetailsViewModel = ViewMangaDetailsViewModel()

@Composable
fun ViewMangaDetails() {

    var thumb by remember { mutableStateOf<ImageBitmap?>(null) }
    var isClicked by remember { mutableStateOf(false) }
    var snackBarData by remember { mutableStateOf<MySnackbarData?>(null) }

    GlobalScope.launch {
        mandatoryDelay()
        if (thumb == null) {
            thumb = ImageRepository.fetchImage(viewMangaDetailsViewModel.manga.thumbnailUrl())
        }
        if (snackBarData != null) {
            delay(2400)
            snackBarData = null
        }
    }

    if (isClicked) {
        StateMachine.start(HOME_CONTROLLER)
    }

    Box {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
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
                                isClicked = true
                            }
                    )
                },
                backgroundColor = MaterialTheme.colors.primary,
                elevation = 8.dp
            )
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                if (thumb != null) {
                    Image(
                        thumb!!,
                        null,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .background(color = MaterialTheme.colors.primary)
                            .padding(4.dp)
                            .width(320.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(8.dp)
                        .width(320.dp)
                ){
                    KeyValue("#", viewMangaDetailsViewModel.manga.id)

                    viewMangaDetailsViewModel.tags.forEach {
                        if (it.tags.isNotEmpty()) {
                            KeyValue(it.tagTitle, it.tags)
                        }
                    }

                    KeyValue("Pages", viewMangaDetailsViewModel.pages.toString())
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Header(viewMangaDetailsViewModel.manga.title)
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(8.dp)
            ) {
                Button(
                    onClick = {
                        // TODO: Read Manga
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Row {
                        Icon(Icons.Default.PlayArrow, null)
                        Text(
                            "Read Manga",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(4.dp, 0.dp)
                        )
                    }
                }
                Button(
                    onClick = {
                        snackBarData = MySnackbarData(
                            "Download will be added in a future release",
                            Red300,
                            Color.Black
                        )
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Row {
                        Icon(Icons.Default.Build, null)
                        Text(
                            "Download Manga",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(4.dp, 0.dp)
                        )
                    }
                }
            }
        }
        if (snackBarData != null) {
            ShowSnackbar(snackBarData!!)
        }
    }
}

@Composable
fun KeyValue(key: String, value: String) {
    Row {
        Text(
            key,
            color = Color.Gray,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(4.dp)
        )
        Text(
            value,
            color = Color.Black,
            fontWeight = FontWeight.Black,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun KeyValue(key: String, tags: List<Tag>) {
    Column {
        Row {
            Spacer(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .background(Color.LightGray)
                    .height(1.dp)
                    .weight(1f)
            )
            Text(
                key,
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(4.dp)
            )
            Spacer(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .background(Color.LightGray)
                    .height(1.dp)
                    .weight(1f)
            )
        }
        StaggeredVerticalGrid(maxColumnWidth = 200.dp) {
            tags.forEach { TagItem(it) }
        }
    }
}

@Composable
fun TagItem(tag: Tag) {
    Row(
        modifier = Modifier
            .clickable {
                // TODO(Search via tags)
            }
            .padding(2.dp)
    ) {
        Text(
            tag.title,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .background(Color.LightGray)
                .clip(RoundedCornerShape(8.dp))
                .padding(2.dp)
                .weight(1f)
        )
        Text(
            tag.count,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .background(Color.DarkGray)
                .clip(RoundedCornerShape(8.dp))
                .padding(2.dp)
        )
    }
}