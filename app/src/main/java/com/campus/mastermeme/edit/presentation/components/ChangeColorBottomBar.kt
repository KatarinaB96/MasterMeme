package com.campus.mastermeme.edit.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.campus.mastermeme.ui.theme.MasterMemeTheme

@Composable
fun ChangeColorBottomBar(
    onColorSelected: (Color) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState)
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Circle(color = Color(0xFFFFEB3B), onColorSelected = onColorSelected)
        Circle(color = Color(0xFFFF5722), onColorSelected = onColorSelected)
        Circle(color = Color(0xFFF44336), onColorSelected = onColorSelected)
        Circle(color = Color(0xFF9C27B0), onColorSelected = onColorSelected)
        Circle(color = Color(0xFF3F51B5), onColorSelected = onColorSelected)
        Circle(color = Color(0xFF2196F3), onColorSelected = onColorSelected)
        Circle(color = Color(0xFFFFEB3B), onColorSelected = onColorSelected)
        Circle(color = Color(0xFFFF5722), onColorSelected = onColorSelected)
        Circle(color = Color(0xFFF44336), onColorSelected = onColorSelected)
        Circle(color = Color(0xFF9C27B0), onColorSelected = onColorSelected)
        Circle(color = Color(0xFF3F51B5), onColorSelected = onColorSelected)
        Circle(color = Color(0xFF2196F3), onColorSelected = onColorSelected)
    }
}

@Composable
fun Circle(
    onColorSelected: (Color) -> Unit = {},
    color: Color,
    size: Dp = 48.dp
) {
    Box(
        modifier = Modifier
            .size(size)
            .background(color = color, shape = CircleShape)
            .clickable {
                onColorSelected(color)
            }
    )
}

@Preview
@Composable
private fun ChangeColorBottomBarPreview() {
    MasterMemeTheme {
        ChangeColorBottomBar()
    }

}