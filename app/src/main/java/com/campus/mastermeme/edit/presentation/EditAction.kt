package com.campus.mastermeme.edit.presentation

import android.content.Context
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import dev.shreyaspatil.capturable.controller.CaptureController

sealed interface EditAction {
    data object OnUndo : EditAction //for undo action
    data object OnRedo : EditAction //for redo action
    data object OnAddText : EditAction //for clicking add text button
    data class OnClickText(val index: Int) :
        EditAction //for clicking text, open changing style, size and color bar

    data object OnChangeFontClick : EditAction //for clicking changing font style
    data object OnChangeSizeClick : EditAction //for clicking changing font size
    data object OnChangeColorClick : EditAction  //for clicking changing font color
    data class OnDoubleTap(val index: Int) : EditAction  //for double tap on text to open "change text" dialog
    data class OnSaveText(val index: Int, val text: String) :
        EditAction //for changing text in "change text" dialog and click "ok" button

    data object OnDismissDialog :
        EditAction //for clicking "cancel" button in "change text" dialog or clicking outside of dialog

    data class OnChangePositionText(val index: Int, val offset: Offset) :
        EditAction //for changing position of text via dragging

    data object OnCancelChangeTextBottomTab :
        EditAction //for clicking "cancel" button in "change text" bottom bar

    data class OnSaveChangeTextBottomTab(
        val context: Context,
        val fileName: String,
        val captureController: CaptureController
    ) :
        EditAction //for clicking "ok" button in "change text" bottom bar

    data object OnClickOutsideOfText :
        EditAction //for clicking outside of text , null to selected text index

    data class OnChangeColorText(val color: Color) : EditAction //for changing color of text
    data class OnChangeSizeText(val size: Float) : EditAction //for changing size of text
    data class OnChangeFontText(val font: FontFamily) : EditAction //for changing font of text

    data class OnDeleteText(val index: Int) : EditAction //for deleting text

    //  data class OnSaveMeme(val context: Context, val bitmap : ImageBitmap, val fileName: String) : EditAction //for saving meme
//  data class OnChangeText(val texts: List<MemeText>) : EditAction
    //   data class OnSelectText(val index: Int) : EditAction

}