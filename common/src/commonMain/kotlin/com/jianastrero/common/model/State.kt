package com.jianastrero.common.model

import androidx.compose.runtime.Composable

data class State(
    val name: String,
    val block: @Composable () -> Unit
)
