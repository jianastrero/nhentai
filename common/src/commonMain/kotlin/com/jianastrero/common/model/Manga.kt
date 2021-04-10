package com.jianastrero.common.model

import com.jianastrero.GALLERY_URL
import com.jianastrero.HOME_URL

data class Manga(
    val id: String,
    val galleryId: String,
    val title: String,
    val thumbnailExtension: String
) {

    fun url() = "${HOME_URL}g/$id"

    fun galleryUrl() = "$GALLERY_URL$galleryId/"

    fun thumbnailUrl() = "${galleryUrl()}thumb.$thumbnailExtension"
}