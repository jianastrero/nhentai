package com.jianastrero.common.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jianastrero.common.model.Tag
import com.jianastrero.common.repository.ImageRepository
import com.jianastrero.common.statemachine.StateMachine
import com.jianastrero.common.view.Header
import com.jianastrero.common.view.StaggeredVerticalGrid
import com.jianastrero.common.viewmodel.ViewMangaDetailsViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal val viewMangaDetailsViewModel = ViewMangaDetailsViewModel()

@Composable
fun ViewMangaDetails() {

    var thumb by remember { mutableStateOf<ImageBitmap?>(null) }

    GlobalScope.launch {
        delay(200)
        thumb = ImageRepository.fetchImage(viewMangaDetailsViewModel.manga.thumbnailUrl())
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Header(viewMangaDetailsViewModel.manga.title)
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
    }

    StateMachine.finish()
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
    println("tag: ${tags.size}")
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