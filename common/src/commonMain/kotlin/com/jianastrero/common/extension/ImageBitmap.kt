package com.jianastrero.common.extension

import androidx.compose.ui.graphics.ImageBitmap

expect suspend fun String.fetchImageBitmap(): ImageBitmap?