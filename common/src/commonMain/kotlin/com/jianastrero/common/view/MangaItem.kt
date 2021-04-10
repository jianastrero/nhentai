package com.jianastrero.common.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.jianastrero.common.extension.fetchImageBitmap
import com.jianastrero.common.model.Manga
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun MangaItem(item: Manga, fillWidth: Boolean = false) {

    var thumb by remember { mutableStateOf<ImageBitmap?>(null) }
    var modifier by remember {
        mutableStateOf(
            Modifier
                .background(Amaranth800, RoundedCornerShape(16.dp))
                .width(240.dp)
                .padding(8.dp)
        )
    }

    GlobalScope.launch {
        thumb = item.thumbnailUrl().fetchImageBitmap()
    }

    if (fillWidth) {
        modifier = Modifier
            .background(Amaranth800, RoundedCornerShape(16.dp))
            .padding(8.dp)
            .fillMaxWidth()
    }

    Box(
        modifier = Modifier.padding(4.dp)
    ) {
        Column(
            modifier = modifier
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
            Text(
                item.title,
                color = Color.White,
                maxLines = 3,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}