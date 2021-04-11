package com.jianastrero.common.model

import com.jianastrero.HOME_URL
import com.jianastrero.IMAGE_GALLERY_URL
import com.jianastrero.THUMBNAIL_GALLERY_URL

data class Manga(
    val id: String,
    val galleryId: String,
    val title: String,
    val thumbnailExtension: String
) {

    fun url() = "${HOME_URL}g/$id"

    fun thumbnailGalleryUrl() = "$THUMBNAIL_GALLERY_URL$galleryId/"

    fun imageGalleryUrl() = "$IMAGE_GALLERY_URL$galleryId/"

    fun thumbnailUrl() = "${thumbnailGalleryUrl()}thumb.$thumbnailExtension"

    fun imageUrl(pageNumber: Int) = "${imageGalleryUrl()}$pageNumber.$thumbnailExtension"
}