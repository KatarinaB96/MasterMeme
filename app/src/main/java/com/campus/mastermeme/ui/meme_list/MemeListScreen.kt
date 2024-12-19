package com.campus.mastermeme.ui.meme_list

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.campus.mastermeme.R
import com.campus.mastermeme.ui.meme_list.components.DeleteMemesDialog
import com.campus.mastermeme.ui.meme_list.components.EmptyContent
import com.campus.mastermeme.ui.meme_list.components.FloatingActionButton
import com.campus.mastermeme.ui.meme_list.components.MemeGrid
import com.campus.mastermeme.ui.meme_list.components.MemeTopBar
import com.campus.mastermeme.ui.meme_list.components.SelectionBottomSheetContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemeListScreen() {
    val imageList = listOf(
        R.drawable.hide_the_pain_harold_7,
        R.drawable.disaster_girl_1,
        R.drawable.jgrgn_44,
        R.drawable.bbctk_29,
        R.drawable.ic_no_memes,
        R.drawable.boardroom_meeting_suggestion_36,
        R.drawable.grus_plan_9,
        R.drawable.c1hh_48,
        R.drawable.zoa8_15,
        R.drawable.yz6z4_22,
        R.drawable.wxtd1_42,
        R.drawable.change_my_mind_5,
        R.drawable.eb198_32,
        R.drawable.u6ylb_19,
        R.drawable.waiting_skeleton_43,
        R.drawable.the_rock_driving_8,
    )

    val context = LocalContext.current

    var isSheetOpen by remember { mutableStateOf(false) }

    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(context.getString(R.string.favourites_first)) }

    var selectedIds by rememberSaveable { mutableStateOf(emptySet<Int>()) }
    val inSelectionMode by remember { derivedStateOf { selectedIds.isNotEmpty() } }

    var showDialog by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()


    if (isSheetOpen) {
        ModalBottomSheet(
            modifier = Modifier
                .fillMaxHeight()
                .windowInsetsPadding(WindowInsets(0.dp)),
            sheetState = sheetState,
            onDismissRequest = {
                isSheetOpen = false
            }
        ) {
            SelectionBottomSheetContent()
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            MemeTopBar(
                inSelectionMode = inSelectionMode,
                selectedIdsCount = selectedIds.size,
                selectedOption = selectedOption,
                imageListNotEmpty = true,
                expanded = expanded,
                onCloseSelection = { selectedIds = emptySet() },
                onDeleteSelection = { showDialog = true },
                onDropdownClick = { expanded = true },
                onDismissDropdown = { expanded = false },
                onOptionSelect = { selectedOption = it; expanded = false }
            )
        },
        floatingActionButton = {
            if (!inSelectionMode) {
                FloatingActionButton { isSheetOpen = true }
            }
        }
    ) { paddingValues ->
        if (imageList.isEmpty()) {
            EmptyContent()
        } else {
            MemeGrid(
                imageList = imageList,
                paddingValues = paddingValues,
                selectedIds = selectedIds,
                onSelectionChange = { selectedIds = it }
            )
        }
    }

    if (showDialog) {
        DeleteMemesDialog(
            memesCount = selectedIds.size,
            onCancel = { showDialog = false },
            onDelete = {
                showDialog = false
            }
        )
    }
}

@Preview
@Composable
fun MemeListScreenPreview() {
    MemeListScreen()
}
