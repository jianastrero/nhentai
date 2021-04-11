package com.jianastrero.common.controller

import androidx.compose.runtime.*
import com.jianastrero.common.model.State
import com.jianastrero.common.state.CHECK_INTERNET_CONNECTION
import com.jianastrero.common.statemachine.StateMachine
import com.jianastrero.common.ui.Home
import com.jianastrero.common.ui.Loading
import com.jianastrero.common.ui.homeViewModel


val FETCH_HOME = State("FETCH_HOME") { fetchHome() }

val HOME = State("HOME") { Home() }

val HOME_CONTROLLER = arrayOf(
    CHECK_INTERNET_CONNECTION,
    FETCH_HOME,
    HOME
)

@Composable
fun fetchHome() {
    var isFetching by remember { mutableStateOf(true) }

    homeViewModel.fetch {
        isFetching = false
    }

    if (isFetching) {
        Loading()
    } else {
        StateMachine.nextState()
    }
}