package com.campus.mastermeme.edit.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.campus.mastermeme.edit.presentation.components.ChangeColorBottomBar
import com.campus.mastermeme.edit.presentation.components.ChangeSizeBottomBar
import com.campus.mastermeme.edit.presentation.components.ChangeStyleBottomBar
import com.campus.mastermeme.edit.presentation.components.ChangeTextDialog
import com.campus.mastermeme.edit.presentation.components.ChangeTextStylesBottomBar
import com.campus.mastermeme.edit.presentation.components.DefaultBottomBar
import com.campus.mastermeme.edit.presentation.components.MemeEditor
import com.campus.mastermeme.edit.presentation.components.TopAppBar
import com.campus.mastermeme.ui.theme.MasterMemeTheme
import dev.shreyaspatil.capturable.capturable
import dev.shreyaspatil.capturable.controller.rememberCaptureController

@Composable
fun EditScreenRoot(
    viewModel: EditViewModel = org.koin.androidx.compose.koinViewModel()
) {

    EditScreen(
        state = viewModel.state,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalComposeApi::class)
@Composable
private fun EditScreen(
    state: EditState,
    onAction: (EditAction) -> Unit
) {

    val captureController = rememberCaptureController()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()




    Scaffold(
        topBar = {
            TopAppBar()

        },
        bottomBar = {
            Column {
                if (state.isChangeFont) {
                    ChangeStyleBottomBar(
                        onChangeFontClick = { font ->
                            onAction(EditAction.OnChangeFontText(font))
                        }
                    )
                }
                if (state.isChangeSize) {
                    ChangeSizeBottomBar(
                        onChangeSize = { size ->
                            onAction(EditAction.OnChangeSizeText(size))
                        },
                        textSize = state.texts[state.selectedTextIndex].textSize
                    )
                }
                if (state.isChangeColor) {
                    ChangeColorBottomBar(
                        onColorSelected = { color ->
                            onAction(EditAction.OnChangeColorText(color))
                        }
                    )
                }
                BottomAppBar(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    if (!state.isClickText) {
                        DefaultBottomBar(
                            onAddTextClick = { onAction(EditAction.OnAddText) },
                            onSaveMemeClick = {
                                onAction(
                                    EditAction.OnSaveChangeTextBottomTab(
                                        context = context,
                                        fileName = "meme23.png",
                                        captureController = captureController
                                    )
                                )
                            },
                        )
                    } else {
                        ChangeTextStylesBottomBar(
                            onCancelClick = { onAction(EditAction.OnCancelChangeTextBottomTab) },
                            onTextStyleClick = { onAction(EditAction.OnChangeFontClick) },
                            onTextSizeClick = { onAction(EditAction.OnChangeSizeClick) },
                            onTextColorClick = { onAction(EditAction.OnChangeColorClick) },

                        )
                    }
                }
            }

        },

        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .clickable( //for disabling ripple effect
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { onAction(EditAction.OnClickOutsideOfText) },
                contentAlignment = Alignment.Center

            ) {

                if (state.isTwiceClick) {
                    ChangeTextDialog(
                        onDismissDialog = { onAction(EditAction.OnDismissDialog) },
                        onSaveText = { index, text ->
                            onAction(
                                EditAction.OnSaveText(
                                    index,
                                    text
                                )
                            )
                        },
                        selectedTextIndex = state.selectedTextIndex,
                        text = state.texts[state.selectedTextIndex].text
                    )
                }

                MemeEditor(
                    texts = state.texts,
                    onPositionChange = { index, offset ->
                        onAction(EditAction.OnChangePositionText(index, offset))
                    },
                    onDoubleTap = {
                        onAction(EditAction.OnDoubleTap)
                    },
                    onSelectText = { index ->
                        onAction(EditAction.OnClickText(index))
                    },
                    selectedTextIndex = state.selectedTextIndex,
                    modifier = Modifier
                        .size(300.dp)
                        .capturable(captureController)
                )
            }
        }
    )


}


@Preview

@Composable

private fun EditScreenPreview() {

    MasterMemeTheme {

        EditScreen(

            state = EditState(),

            onAction = {}

        )

    }

}

