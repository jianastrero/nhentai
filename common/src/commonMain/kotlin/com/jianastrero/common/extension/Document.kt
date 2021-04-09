package com.jianastrero.common.extension

import org.jsoup.nodes.Element

fun Element.getFirstElementByClass(className: String) = getElementsByClass(className).first()

fun Element.getFirstElementByTag(tagName: String) = getElementsByTag(tagName).first()