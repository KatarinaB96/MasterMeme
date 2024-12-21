package com.campus.mastermeme.edit.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import com.campus.mastermeme.edit.presentation.model.MemeText

class EditViewModel : ViewModel() {
    var state by mutableStateOf(EditState())
        private set

    fun onAction(action: EditAction) {
        when (action) {
            is EditAction.OnAddText -> {
                state = state.copy(
                    isAddText = true, texts = state.texts + MemeText(
                        text = "TAP TWICE TO EDIT",
                        position = Offset(0f, 0f),
                    )
                )
            }

            is EditAction.OnClickText -> {
                state = state.copy(
                    isClickText = true,
                    selectedTextIndex = action.index
                )
            }

            is EditAction.OnChangeFontClick -> {
                state = state.copy(isChangeFont = true, isChangeSize = false, isChangeColor = false)
            }

            is EditAction.OnChangeSizeClick -> {
                state = state.copy(isChangeSize = true, isChangeFont = false, isChangeColor = false)
            }

            is EditAction.OnChangeColorClick -> {
                state = state.copy(isChangeColor = true, isChangeFont = false, isChangeSize = false)
            }

            is EditAction.OnSaveText -> {
                val texts = state.texts.toMutableList()
                texts[action.index] = texts[action.index].copy(text = action.text)
                state = state.copy(texts = texts)
            }


            EditAction.OnDismissDialog -> {
                state = state.copy(
                    isTwiceClick = false,
                )
            }

            EditAction.OnDoubleTap -> {
                state = state.copy(
                    isTwiceClick = true,
                    selectedTextIndex = state.texts.size - 1
                )
            }

            EditAction.OnRedo -> TODO()
            EditAction.OnUndo -> TODO()
            is EditAction.OnChangePositionText -> {
                val texts = state.texts.toMutableList()
                texts[action.index] = texts[action.index].copy(position = action.offset)
                state = state.copy(texts = texts)
            }

            EditAction.OnCancelChangeTextBottomTab -> {
                state = state.copy(
                    isChangeFont = false,
                    isChangeSize = false,
                    isChangeColor = false,
                    isClickText = false,
                    selectedTextIndex = -1
                )
            }

            EditAction.OnSaveChangeTextBottomTab -> TODO()
            EditAction.OnClickOutsideOfText -> {
                state = state.copy(
                    isClickText = false,
                    selectedTextIndex = -1,
                    isChangeFont = false,
                    isChangeSize = false,
                    isChangeColor = false
                )
            }

            is EditAction.OnChangeColorText -> {
                val texts = state.texts.toMutableList()
                texts[state.selectedTextIndex] =
                    texts[state.selectedTextIndex].copy(textColor = action.color)
                state = state.copy(texts = texts)
            }

            is EditAction.OnChangeSizeText -> {
                val texts = state.texts.toMutableList()
                texts[state.selectedTextIndex] =
                    texts[state.selectedTextIndex].copy(textSize = action.size)
                state = state.copy(texts = texts)
            }
        }
    }
}