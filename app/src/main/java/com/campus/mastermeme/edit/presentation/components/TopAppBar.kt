package com.campus.mastermeme.edit.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.campus.mastermeme.R
import com.campus.mastermeme.ui.theme.MasterMemeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        modifier = Modifier.fillMaxWidth(),
        navigationIcon = {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = stringResource(R.string.back),
                tint = Color.Black
            )
        },

        title = { Text("New Meme", textAlign = TextAlign.Center) }
    )
}

@Preview
@Composable
private fun TopAppBarPreview() {
    MasterMemeTheme {
        TopAppBar()
    }
}