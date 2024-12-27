package com.campus.mastermeme.memes.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.campus.mastermeme.R
import com.campus.mastermeme.core.domain.models.Meme
import com.campus.mastermeme.core.presentation.ui.theme.PrimaryContainer

@Composable
fun MemeItem(
    meme: Meme,
    isSelected: Boolean,
    inSelectionMode: Boolean,
    onFavoriteToggle: (Meme) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(176.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
//        Image(
//            painter = rememberAsyncImagePainter(model = meme.imageUri),
//            contentDescription = null,
//            contentScale = ContentScale.Crop,
//            modifier = Modifier.fillMaxSize()
//        )

        Image(
            painter =  painterResource(meme.imageUri),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
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
                painter = if (isSelected) painterResource(id = R.drawable.ic_check_filled) else painterResource(id = R.drawable.ic_check_empty),
                contentDescription = if (isSelected) "Selected" else "Not selected",
                tint = PrimaryContainer,
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
                imageVector = if (meme.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = if (meme.isFavorite) "not favorite" else "Favorite",
                tint = PrimaryContainer,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(horizontal = 2.dp, vertical = 3.dp)
                    .clickable {
                        onFavoriteToggle(meme)
                    }
            )
        }
    }
}

@Preview
@Composable
fun MemeItemSelectionModePreview() {
    MemeItem(meme = Meme(0,"n", R.drawable.i_bet_hes_thinking_about_other_women_10, true, 232L), true, true, {})
}
