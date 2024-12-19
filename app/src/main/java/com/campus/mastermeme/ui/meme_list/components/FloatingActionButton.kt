package com.campus.mastermeme.ui.meme_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.campus.mastermeme.R
import com.campus.mastermeme.ui.theme.GradientColor2
import com.campus.mastermeme.ui.theme.GradientPressed1
import com.campus.mastermeme.ui.theme.GradientPressed2
import com.campus.mastermeme.ui.theme.PrimaryContainer

@Composable
fun FloatingActionButton(onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val primaryButtonGradientDefault = listOf(
        PrimaryContainer, GradientColor2
    )

    val primaryButtonGradientPressed = listOf(
        GradientPressed1, GradientPressed2
    )
    val gradientColors = if (isPressed) {
        primaryButtonGradientPressed
    } else {
        primaryButtonGradientDefault
    }

    IconButton(
        onClick = onClick,
        modifier = Modifier
            .background(
                brush = Brush.horizontalGradient(
                    colors = gradientColors
                ),
                shape = RoundedCornerShape(14.dp)
            )
            .size(56.dp),
        interactionSource = interactionSource
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(R.string.add),
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview
@Composable
fun FloatingActionButtonPreview() {
    FloatingActionButton({})
}