package com.jianastrero.common.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jianastrero.common.model.MySnackbarData

@Composable
fun ShowSnackbar(snackBarData: MySnackbarData) {
    Box(
        modifier = Modifier
            .padding(24.dp, 8.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Snackbar(
            backgroundColor = snackBarData.background,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Text(
                snackBarData.message,
                color = snackBarData.color
            )
        }
    }
}