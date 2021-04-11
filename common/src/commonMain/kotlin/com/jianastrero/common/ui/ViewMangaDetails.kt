package com.jianastrero.common.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.jianastrero.common.statemachine.StateMachine


@Composable
fun ViewMangaDetails() {
    Column {
        Text("View Manga Details")
    }

    StateMachine.finish()
}