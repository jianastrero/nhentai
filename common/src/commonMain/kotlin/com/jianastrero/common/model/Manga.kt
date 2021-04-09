package com.jianastrero.common.model

data class Manga(
    val id: String,
    val galleryId: String,
    val title: String,
    val tags: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Manga

        if (id != other.id) return false
        if (galleryId != other.galleryId) return false
        if (title != other.title) return false
        if (!tags.contentEquals(other.tags)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + galleryId.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + tags.contentHashCode()
        return result
    }
}