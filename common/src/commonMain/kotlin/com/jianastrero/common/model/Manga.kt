package com.jianastrero.common.model

import com.jianastrero.common.ui.GALLERY_URL
import com.jianastrero.common.ui.HOME_URL

data class Manga(
    val id: String,
    val galleryId: String,
    val title: String
) {

    fun url() = "${HOME_URL}g/$id"

    fun galleryUrl() = "$GALLERY_URL$galleryId/"

    fun thumbnailUrl() = "${galleryUrl()}thumb.jpg"
}