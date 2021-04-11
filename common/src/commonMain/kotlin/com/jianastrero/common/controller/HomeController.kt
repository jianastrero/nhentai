package com.jianastrero.common.controller

import androidx.compose.runtime.Composable
import com.jianastrero.common.model.State
import com.jianastrero.common.state.CHECK_INTERNET_CONNECTION
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
    Loading()
    homeViewModel.fetch()
}