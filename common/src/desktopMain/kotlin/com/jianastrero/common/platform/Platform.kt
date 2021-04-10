package com.jianastrero.common.platform

import com.jianastrero.common.enumeration.Platform

actual fun getAppName(): String {
    return "NHentai Desktop"
}

actual fun getPlatform(): Platform = Platform.DESKTOP