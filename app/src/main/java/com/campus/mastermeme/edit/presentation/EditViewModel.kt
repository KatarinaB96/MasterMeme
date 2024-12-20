package com.campus.mastermeme.edit.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class EditViewModel : ViewModel() {
    var state by mutableStateOf(EditState())
        private set

    fun onAction(action: EditAction) {
        when (action) {
            is EditAction.AddText -> {
                state = state.copy(isAddText = true)
            }

            is EditAction.ClickText -> {
                state = state.copy(isClickText = true)
            }

            is EditAction.ChangeFont -> {
                state = state.copy(isChangeFont = true)
            }

            is EditAction.ChangeSize -> {
                state = state.copy(isChangeSize = true)
            }

            is EditAction.ChangeColor -> {
                state = state.copy(isChangeColor = true)
            }

            is EditAction.ChangeText -> {
                state = state.copy(texts = action.texts)
            }

            is EditAction.SelectText -> {
                state = state.copy(selectedTextIndex = action.index)
            }
        }
    }
}