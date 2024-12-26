package com.campus.mastermeme.memes.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.campus.mastermeme.R
import com.campus.mastermeme.core.domain.models.Meme
import com.campus.mastermeme.memes.presentation.MemeListState

@Composable
fun MemeGrid(
    state: MemeListState,
    paddingValues: PaddingValues,
    onLongPress: (Meme) -> Unit,
    onSelectionChange: (List<Meme>) -> Unit,
    onFavoriteToggle: (Meme) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(22.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(state.memes, key = { it.id }) { meme ->
            val isSelected = state.selectedMemes.contains(meme)

            MemeItem(
                meme = meme,
                isSelected = isSelected,
                inSelectionMode = state.inSelectionMode,
                onFavoriteToggle = { onFavoriteToggle(meme) },
                modifier = if (state.inSelectionMode) {
                    Modifier.toggleable(
                        value = isSelected,
                        onValueChange = {
                            val updatedSelection = if (it) {
                                state.selectedMemes + meme
                            } else {
                                state.selectedMemes - meme
                            }
                            onSelectionChange(updatedSelection)
                        },
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    )
                } else {
                    Modifier.pointerInput(Unit) {
                        detectTapGestures(
                            onLongPress = { onLongPress(meme) }
                        )
                    }

                }
            )
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
