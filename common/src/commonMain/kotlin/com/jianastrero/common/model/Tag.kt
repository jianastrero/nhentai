package com.jianastrero.common.model

import com.jianastrero.HOME_URL

data class Tag(
    val title: String,
    val urlSlug: String,
    val count: String
) {
    fun getUrl() = "${HOME_URL}tag/$urlSlug"
}
