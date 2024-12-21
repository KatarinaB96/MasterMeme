package com.campus.mastermeme.edit.presentation.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

data class MemeText(
    var text: String,
    var textSize: Float = 24f,
    var textColor: Color = Color.White,
    var textFont: String = "Roboto",
    var position: Offset
)