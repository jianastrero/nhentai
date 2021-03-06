package com.jianastrero.common.state

import androidx.compose.runtime.*
import com.jianastrero.common.extension.mandatoryDelay
import com.jianastrero.common.model.State
import com.jianastrero.common.statemachine.StateMachine
import com.jianastrero.common.ui.Loading
import com.jianastrero.common.ui.NoInternetConnection
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL

val CHECK_INTERNET_CONNECTION = State(
    "CHECK_INTERNET_CONNECTION"
) {
    checkInternetConnection()
}

@Composable
fun checkInternetConnection() {
    var status by remember { mutableStateOf(-1) }

    GlobalScope.launch {
        mandatoryDelay()
        val connection: HttpURLConnection
        try {
            connection = URL("https://www.google.com/").openConnection() as HttpURLConnection
            connection.connectTimeout = 1000
            connection.connect()
            status = 1
        } catch (e: Exception) {
            e.printStackTrace()
            status = 0
        }
    }

    when (status) {
        -1 -> Loading()
        0 -> {
            NoInternetConnection()
            StateMachine.finish()
        }
        1 -> StateMachine.nextState()
    }
}