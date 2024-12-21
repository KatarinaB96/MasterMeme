package com.campus.mastermeme.edit.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.campus.mastermeme.R
import com.campus.mastermeme.ui.theme.MasterMemeTheme

@Composable
fun ChangeStyleBottomBar(modifier: Modifier = Modifier) {
    val fontList = listOf(
        R.drawable.frame_42,
        R.drawable.frame_43,
        R.drawable.frame_44,
        R.drawable.frame_45,
        R.drawable.frame_46,
        R.drawable.frame_42,
        R.drawable.frame_43,
        R.drawable.frame_44,
        R.drawable.frame_45,
        R.drawable.frame_46,
        R.drawable.frame_42,
        R.drawable.frame_43,
        R.drawable.frame_44,
        R.drawable.frame_45,
        R.drawable.frame_46,
    )
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .horizontalScroll(scrollState)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        fontList.forEach { font ->
            Image(
                painter = painterResource(id = font),
                contentDescription = "My Local Image",
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Crop
            )
        }

    }

}

@Preview
@Composable
private fun ChangeStyleBottomBarPreview() {
    MasterMemeTheme {
        ChangeStyleBottomBar(

        )
    }

}