package com.campus.mastermeme.memes.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.campus.mastermeme.R
import com.campus.mastermeme.core.domain.models.Meme
import com.campus.mastermeme.memes.presentation.components.DeleteMemesDialog
import com.campus.mastermeme.memes.presentation.components.EmptyContent
import com.campus.mastermeme.memes.presentation.components.FloatingActionButton
import com.campus.mastermeme.memes.presentation.components.MemeGrid
import com.campus.mastermeme.memes.presentation.components.MemeTopBar
import com.campus.mastermeme.memes.presentation.components.SelectionBottomSheetContent
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun MemeListScreenRoot(
    viewModel: MemeListViewModel = koinViewModel(),
    onMemeClick: (Meme) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MemeListScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is MemeListAction.OnMemeClick -> MemeListAction.OnMemeClick(action.meme)
                else -> Unit
            }
            viewModel.onAction(action)
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemeListScreen(
    state: MemeListState,
    onAction: (MemeListAction) -> Unit,
) {
    val context = LocalContext.current
    var isSheetOpen by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    var showDialog by remember { mutableStateOf(false) }

    // val sheetState = rememberModalBottomSheetState(
    //    confirmValueChange = { true },
    //  )

    var skipPartiallyExpanded by remember { mutableStateOf(true) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded, confirmValueChange = { true })

    val coroutineScope = rememberCoroutineScope()

    fun openBottomSheetFullScreen() {
        skipPartiallyExpanded = true
        coroutineScope.launch() {
            sheetState.expand()
        }

    }

    LaunchedEffect(true) {
        skipPartiallyExpanded = false
    }


    if (isSheetOpen) {
        ModalBottomSheet(
            modifier = Modifier.fillMaxSize(),
            sheetState = sheetState,
            onDismissRequest = {
                isSheetOpen = false
            }
        ) {
            SelectionBottomSheetContent(
                state,
                openBottomSheetFullScreen = { openBottomSheetFullScreen() },
                onMemeSelected = { selectedMeme ->
                    onAction(MemeListAction.OnAddMemeClick(selectedMeme))
                    isSheetOpen = false
                },
                onSearchQueryChange = {
                    onAction(MemeListAction.OnSearchQueryChange(it))
                }
            )
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            MemeTopBar(
                inSelectionMode = state.inSelectionMode,
                selectedIdsCount = state.selectedMemes.size,
                imageListNotEmpty = state.memes.isNotEmpty(),
                expanded = expanded,
                onCloseSelection = {
                    onAction(MemeListAction.ExitSelectionMode)
                },
                onShowDialog = { showDialog = true },
                onDropdownClick = { expanded = true },
                onDismissDropdown = { expanded = false },
                onOptionSelect = { option ->
                    expanded = false
                    val sortType = if (option == context.getString(R.string.favourites_first)) SortType.IS_FAVORITE else SortType.NEWEST
                    onAction(MemeListAction.OnSortTypeChanged(sortType))
                }
            )
        },
        floatingActionButton = {
            if (!state.inSelectionMode) {
                FloatingActionButton {
                    isSheetOpen = true
                    skipPartiallyExpanded = false

                }
            }
        }
    ) { paddingValues ->
        if (state.memes.isEmpty()) {
            EmptyContent()
        } else {
            MemeGrid(
                state = state,
                paddingValues = paddingValues,
                onImageClick = { meme ->
                    onAction(MemeListAction.OnMemeClick(meme))
                },
                onLongPress = { meme ->
                    onAction(MemeListAction.OnSelectionMode(meme))
                },
                onSelectionChange = { updatedSelection ->
                    if (updatedSelection.isEmpty()) {
                        onAction(MemeListAction.ExitSelectionMode)
                    } else {
                        onAction(MemeListAction.OnSelectionChange(updatedSelection))
                    }
                },
                onFavoriteToggle = { meme ->
                    onAction(MemeListAction.OnFavoriteToggle(meme))
                }
            )

        }
    }

    if (showDialog) {
        DeleteMemesDialog(
            memesCount = state.selectedMemes.size,
            onCancel = {
                showDialog = false },
            onDelete = {
                showDialog = false
                onAction(MemeListAction.DeleteMemeList(state.selectedMemes))
            }
        )
    }
}

@Preview
@Composable
fun MemeListScreenPreview() {
    MemeListScreen(state = MemeListState(),{})
}
