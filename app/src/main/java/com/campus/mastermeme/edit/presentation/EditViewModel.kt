package com.campus.mastermeme.edit.presentation

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.campus.mastermeme.edit.presentation.model.MemeText
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class EditViewModel : ViewModel() {
    var state by mutableStateOf(EditState())
        private set


    @OptIn(ExperimentalComposeApi::class)
    fun onAction(action: EditAction) {
        when (action) {
            is EditAction.OnAddText -> {

                loadCurrentStateToUndoStack()

                val texts = state.texts.toMutableList()
                texts.add(
                    MemeText(
                        text = "TAP TWICE TO EDIT",
                        position = Offset(0f, 0f),
                    )
                )

                state = state.copy(
                    isAddText = true,
                    texts = texts,
                    isRedoEnabled = state.redoStack.isNotEmpty(),
                )

                println("undoStack OnAddText: ${state.undoStack}")
            }

            is EditAction.OnClickText -> {
                loadCurrentStateToUndoStack()

                state = state.copy(
                    isClickText = true,
                    selectedTextIndex = action.index,
                    tempMemeText = state.texts[action.index],
                )

                println("undoStack OnClickText: ${state.undoStack}")
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
                loadCurrentStateToUndoStack()

                val texts = state.texts.toMutableList()
                texts[action.index] = texts[action.index].copy(text = action.text)
                state = state.copy(
                    texts = texts,
                    selectedTextIndex = -1,
                )
            }


            EditAction.OnDismissDialog -> {
                state = state.copy(
                    isTwiceClick = false,
                )
            }

            is EditAction.OnDoubleTap -> {
                state = state.copy(
                    isTwiceClick = true,
                    selectedTextIndex = action.index
                )
            }

            EditAction.OnRedo -> {
                val texts = state.redoStack.last()

                loadCurrentStateToUndoStack()

                val redoStack = state.redoStack
                redoStack.removeLast()

                state = state.copy(
                    texts = texts,
                    redoStack = redoStack,
                    isRedoEnabled = state.redoStack.isNotEmpty()
                )
                println("undoStack OnRedo: ${state.undoStack}")

            }

            EditAction.OnUndo -> {
                loadCurrentStateToRedoStack()

                val texts = state.undoStack.last()

                val undoStack = state.undoStack
                undoStack.removeLast()

                state = state.copy(
                    texts = texts,
                    undoStack = undoStack,
                    isUndoEnabled = state.undoStack.isNotEmpty(),
                    isRedoEnabled = state.redoStack.isNotEmpty()
                )
                println("undoStack OnUndo: ${state.undoStack}")

            }


            is EditAction.OnChangePositionText -> {
                loadCurrentStateToUndoStack()

                val texts = state.texts.toMutableList()
                texts[action.index] = texts[action.index].copy(position = action.offset)

                state = state.copy(
                    texts = texts,
                )
                println("undoStack OnChangePositionText: ${state.undoStack}")

            }

            EditAction.OnCancelChangeTextBottomTab -> {
                state = state.copy(
                    isChangeFont = false,
                    isChangeSize = false,
                    isChangeColor = false,
                    isClickText = false,
                    texts = state.texts.mapIndexed { index, it ->
                        if (index == state.selectedTextIndex) {
                            it.copy(
                                textFont = state.tempMemeText?.textFont ?: it.textFont,
                                textSize = state.tempMemeText?.textSize ?: it.textSize,
                                textColor = state.tempMemeText?.textColor ?: it.textColor
                            )
                        } else {
                            it
                        }
                    },
                    selectedTextIndex = -1,
                )

            }

            is EditAction.OnSaveChangeTextBottomTab -> {
                state = state.copy(
                    isChangeFont = false,
                    isChangeSize = false,
                    isChangeColor = false,
                    isClickText = false,
                    selectedTextIndex = -1,
                )
            }

            is EditAction.OnSaveMemeDefaultBottomTab -> {
                viewModelScope.launch {
                    val bitmapAsync = action.captureController.captureAsync()
                    try {
                        val bitmap = bitmapAsync.await() // Wait for the bitmap to be captured
                            .asAndroidBitmap() // Convert to Android Bitmap
                        saveBitmapToCache(
                            context = action.context,
                            bitmap = bitmap,
                            fileName = action.fileName
                        )
                    } catch (error: Throwable) {
                        // Error occurred, do something.
                    }
                }
            }

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

            is EditAction.OnChangeFontText -> {
                val texts = state.texts.toMutableList()
                texts[state.selectedTextIndex] =
                    texts[state.selectedTextIndex].copy(textFont = action.font)
                state = state.copy(texts = texts)
            }

            is EditAction.OnDeleteText -> {
                val texts = state.texts.toMutableList()
                texts.removeAt(action.index)
                state = state.copy(
                    texts = texts,
                    isClickText = false,
                    selectedTextIndex = -1,
                    isChangeFont = false,
                    isChangeSize = false,
                    isChangeColor = false
                )
            }

            EditAction.OnBackClick -> {
                state = state.copy(isBackClickPopup = true)
            }

            EditAction.OnDismissBackDialog -> {
                state = state.copy(isBackClickPopup = false)
            }


            else -> Unit
        }
    }

    fun saveBitmapToCache(context: Context, bitmap: Bitmap, fileName: String): File? {
        val cacheDir = context.cacheDir // Get the cache directory
        val file = File(cacheDir, fileName) // Create a file in the cache directory

        try {
            FileOutputStream(file).use { outputStream ->
                // Compress the bitmap into the file as a PNG
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            }
        } catch (e: IOException) {
            e.printStackTrace()
            return null // Return null if there was an error
        }

        return file // Return the saved file
    }


    private fun loadCurrentStateToUndoStack() {
        val undoStack = state.undoStack
        if (undoStack.size == 5) {
            undoStack.removeFirst()
        }

        undoStack.addLast(state.texts.toMutableList())

        state = state.copy(
            undoStack = undoStack,
            isUndoEnabled = state.undoStack.isNotEmpty(),
        )
    }

    private fun loadCurrentStateToRedoStack() {
        val redoStack = state.redoStack
        if (redoStack.size == 5) {
            redoStack.removeFirst()
        }

        redoStack.addLast(state.texts.toMutableList())

        state = state.copy(
            redoStack = redoStack,
            isRedoEnabled = state.redoStack.isNotEmpty(),
        )
    }


}