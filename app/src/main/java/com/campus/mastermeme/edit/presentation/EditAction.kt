package com.campus.mastermeme.edit.presentation

import com.campus.mastermeme.edit.presentation.model.MemeText

sealed interface EditAction {
    object AddText : EditAction
    object ClickText : EditAction
    object ChangeFont : EditAction
    object ChangeSize : EditAction
    object ChangeColor : EditAction
    data class ChangeText(val texts: List<MemeText>) : EditAction
    data class SelectText(val index: Int) : EditAction

}