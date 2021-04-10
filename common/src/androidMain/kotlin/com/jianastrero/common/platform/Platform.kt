package com.jianastrero.common.platform

import com.jianastrero.common.enumeration.Platform

actual fun getAppName(): String {
    return "NHentai Android"
}

actual fun getPlatform(): Platform = Platform.ANDROID