package com.jianastrero.common

import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.util.stream.Stream

fun String.fetch(block: (Stream<String>) -> Unit) {
    val connection = URL(this).openConnection() as HttpURLConnection

    try {
        connection.inputStream.use { inputStream ->
            inputStream.bufferedReader().use { bufferedReader ->
                block(bufferedReader.lines())
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        connection.disconnect()
    }
}