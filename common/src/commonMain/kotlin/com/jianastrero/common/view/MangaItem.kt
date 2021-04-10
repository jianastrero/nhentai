package com.jianastrero.common.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jianastrero.common.color.Amaranth800
import com.jianastrero.common.model.Manga
import com.jianastrero.common.repository.ImageRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun MangaItem(item: Manga, fillWidth: Boolean = false, maxLines: Int = 3, onClick: () -> Unit) {

    var thumb by remember { mutableStateOf<ImageBitmap?>(null) }
    var modifier by remember {
        mutableStateOf(
            Modifier
                .width(240.dp)
        )
    }

    GlobalScope.launch {
        thumb = ImageRepository.fetchImage(item.thumbnailUrl())
    }

    if (fillWidth) {
        modifier = Modifier
            .fillMaxWidth()
    }

    Box(
        modifier = Modifier.padding(4.dp)
    ) {
        Column(
            modifier = modifier
                .background(Amaranth800, RoundedCornerShape(16.dp))
                .clickable {
                    onClick()
                }
                .padding(8.dp)
        ) {
            if (thumb != null) {
                Image(
                    thumb!!,
                    null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .fillMaxWidth()
                )
            }
            if (maxLines > 0) {
                Text(
                    item.title,
                    color = Color.White,
                    maxLines = maxLines,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                Text(
                    item.title,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}