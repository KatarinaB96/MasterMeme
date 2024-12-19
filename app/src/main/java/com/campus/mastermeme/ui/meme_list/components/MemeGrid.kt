package com.campus.mastermeme.ui.meme_list.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.campus.mastermeme.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MemeGrid(
    imageList: List<Int>,
    paddingValues: PaddingValues,
    selectedIds: Set<Int>,
    onSelectionChange: (Set<Int>) -> Unit
) {
    val inSelectionMode = selectedIds.isNotEmpty()
    var selectedImage by remember { mutableStateOf<Int?>(null) }
    var showEnlargedImage by remember { mutableStateOf(false) }


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(22.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        itemsIndexed(imageList) { index, resId ->
            val selected = selectedIds.contains(resId)

            MemeItem(
                photo = resId,
                selected = selected,
                inSelectionMode = inSelectionMode,
                modifier = if (inSelectionMode) {
                    Modifier.toggleable(
                        value = selected,
                        onValueChange = {
                            val updatedSelection = if (it) selectedIds + resId else selectedIds - resId
                            onSelectionChange(updatedSelection)
                        },
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    )
                } else {
                    Modifier.combinedClickable(
                        onClick = {
                            selectedImage = resId
                            showEnlargedImage = true
                        },
                        onLongClick = {
                            onSelectionChange(selectedIds + resId)
                        }
                    )
                }
            )
        }
    }

    selectedImage?.let {
        FullScreenImageDialog(image = it) {
            selectedImage = null
        }
    }
}

@Composable
fun FullScreenImageDialog(image: Int, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .background(Color.Black)
                .padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(bottom = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.close),
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(350.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun FullScreenImagePrevise() {
    FullScreenImageDialog(R.drawable.bbctk_29, {})
}
