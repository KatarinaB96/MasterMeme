package com.campus.mastermeme.edit.presentation

import androidx.compose.runtime.mutableStateListOf
import com.campus.mastermeme.edit.presentation.model.MemeText

data class EditState(
    val isAddText: Boolean = false,
    val isClickText: Boolean = false,
    val isTwiceClick: Boolean = false,
    val isChangeFont: Boolean = false,
    val isChangeSize: Boolean = false,
    val isChangeColor: Boolean = false,
    val texts: List<MemeText> = emptyList(),
    val tempMemeText: MemeText? = null, //for changing text font, size and color before saving
    val undoStack: ArrayDeque<List<MemeText>> = ArrayDeque(),// for undo operation, like stack
    val redoStack: ArrayDeque<List<MemeText>> = ArrayDeque(),// for redo operation, like stack
    val selectedTextIndex: Int = -1,
    val isBackClickPopup: Boolean = false,
    val isUndoEnabled: Boolean = false,
    val isRedoEnabled: Boolean = false,
    val isSaveOrShareBottomSheetOpen: Boolean = false,
    val visiblePermissionDialogQueue: List<String> = mutableStateListOf<String>()

)
