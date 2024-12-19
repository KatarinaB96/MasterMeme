package com.campus.mastermeme.ui.meme_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.campus.mastermeme.R
import com.campus.mastermeme.ui.theme.PrimaryContainer

@Composable
fun MemeItem(photo: Int, selected: Boolean, inSelectionMode: Boolean, modifier: Modifier) {
    var isFavorite by remember { mutableStateOf(true) }
    Box(
        modifier = modifier
            .size(176.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = painterResource(photo),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        if (inSelectionMode) {
            val gradientSelection = Brush.linearGradient(
                colorStops = arrayOf(
                    0.0f to Color.Transparent,
                    0.8f to Color.Transparent,
                    1.0f to Color(0xFF332b28)
                ),
                start = Offset(0f, Offset.Infinite.y),
                end = Offset(Offset.Infinite.x, 0f),
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush = gradientSelection)
            )
            Icon(
                painter = if (selected) painterResource(id = R.drawable.ic_check_filled)
                else painterResource(id = R.drawable.ic_check_empty),
                tint = PrimaryContainer,
                contentDescription = null,
                modifier = Modifier.align(Alignment.TopEnd)
            )
        } else {
            val favorite = Brush.linearGradient(
                colorStops = arrayOf(
                    0.0f to Color.Transparent,
                    0.8f to Color.Transparent,
                    1.0f to Color(0xFF332b28)
                ),
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush = favorite)
            )

            Icon(
                painter = if (isFavorite) painterResource(id = R.drawable.ic_selected_favorite)
                else painterResource(id = R.drawable.ic_empty_favorite),
                contentDescription = stringResource(R.string.favorite),
                tint = PrimaryContainer,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(horizontal = 2.dp, vertical = 3.dp)
                    .clickable {
                        isFavorite = !isFavorite
                    }
            )
        }
    }
}

@Preview
@Composable
fun MemeItemSelectionModePreview() {
    MemeItem(R.drawable.bbctk_29, true, true, modifier = Modifier)
}

@Preview
@Composable
fun MemeItemPreview() {
    MemeItem(R.drawable.bbctk_29, true, false, modifier = Modifier)
}