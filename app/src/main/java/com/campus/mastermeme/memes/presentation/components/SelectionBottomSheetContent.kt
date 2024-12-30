package com.campus.mastermeme.memes.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.campus.mastermeme.R
import com.campus.mastermeme.core.domain.models.AvailableMeme
import com.campus.mastermeme.memes.presentation.MemeListState

@Composable
fun SelectionBottomSheetContent(
    state: MemeListState,
    openBottomSheetFullScreen: () -> Unit,
    onMemeSelected: (AvailableMeme) -> Unit,
    onSearchQueryChange: (String) -> Unit,
) {
    var isSearchVisible by remember { mutableStateOf(false) }
    val memes = if (state.searchQuery.isEmpty()) state.availableMeme else state.searchResults

    Column {
        HeaderWithSearch(
            state.searchQuery,
            openBottomSheetFullScreen = openBottomSheetFullScreen,
            isSearchVisible = isSearchVisible,
            onSearchVisibilityChange = { isSearchVisible = !isSearchVisible },
            onSearchQueryChange = onSearchQueryChange,
            memeListSize = memes.size,
            onBackPressed = { isSearchVisible = false }
        )

        if (!isSearchVisible) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.choose_template_for_your_next_meme_masterpiece),
                color = Color.White,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp),
                lineHeight = 16.39.sp
            )
        }

        Spacer(modifier = Modifier.height(42.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(horizontal = 22.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(if (isSearchVisible) memes else state.availableMeme) { meme ->
                BottomSheetItem(
                    resId = meme.resId,
                    onClick = {
                        onMemeSelected(meme)
                    }
                )
            }
        }
    }
}

@Composable
fun HeaderWithSearch(
    searchQuery: String,
    openBottomSheetFullScreen: () -> Unit,
    isSearchVisible: Boolean,
    onSearchVisibilityChange: () -> Unit,
    onSearchQueryChange: (String) -> Unit,
    memeListSize: Int,
    onBackPressed: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (isSearchVisible) {
            SearchTextField(
                searchQuery,
                onSearchQueryChange,
                onImeSearch = {
                    keyboardController?.hide()
                },
                memeListSize,
                onBackPressed = onBackPressed
            )

        } else {
            onSearchQueryChange("")
            Text(
                text = stringResource(R.string.choose_template),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                lineHeight = 21.86.sp
            )
            IconButton(onClick = {
                onSearchVisibilityChange()
                openBottomSheetFullScreen()
            }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search),
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Composable
fun BottomSheetItem(resId: Int, onClick: () -> Unit) {
    Image(
        painter = painterResource(resId),
        contentDescription = stringResource(R.string.image),
        modifier = Modifier
            .size(176.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() },
        contentScale = ContentScale.FillBounds
    )
}

@Preview
@Composable
fun SelectionBottomSheetPreview() {
    SelectionBottomSheetContent(
        state = MemeListState(),
        openBottomSheetFullScreen = {},
        onMemeSelected = {},
        onSearchQueryChange = {}
    )
}

@Preview
@Composable
fun ItemPreview() {
    BottomSheetItem(R.drawable.bbctk_29, {})
}

@Preview
@Composable
fun HeaderWithSearchPreview() {
    HeaderWithSearch("", {}, true, {}, {}, 0, {})
}
