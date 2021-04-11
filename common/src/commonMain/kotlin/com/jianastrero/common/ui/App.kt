package com.jianastrero.common.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jianastrero.common.controller.HOME_CONTROLLER
import com.jianastrero.common.statemachine.StateMachine
import com.jianastrero.common.theme.NHentaiTheme

@Composable
fun App(onTitleChange: (String) -> Unit) {
    NHentaiTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            StateMachine.stateHolder()
        }
    }

    StateMachine.start(HOME_CONTROLLER)
}