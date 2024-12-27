package com.campus.mastermeme.edit.presentation

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build.VERSION.SDK_INT
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import com.campus.mastermeme.R
import com.campus.mastermeme.edit.presentation.components.ChangeColorBottomBar
import com.campus.mastermeme.edit.presentation.components.ChangeSizeBottomBar
import com.campus.mastermeme.edit.presentation.components.ChangeStyleBottomBar
import com.campus.mastermeme.edit.presentation.components.ChangeTextDialog
import com.campus.mastermeme.edit.presentation.components.ChangeTextStylesBottomBar
import com.campus.mastermeme.edit.presentation.components.DefaultBottomBar
import com.campus.mastermeme.edit.presentation.components.LeaveEditorDialog
import com.campus.mastermeme.edit.presentation.components.MemeEditor
import com.campus.mastermeme.edit.presentation.components.PermissionDialog
import com.campus.mastermeme.edit.presentation.components.SaveOrShareBottomSheet
import com.campus.mastermeme.edit.presentation.components.StoragePermissionTextProvider
import com.campus.mastermeme.edit.presentation.components.TopAppBar
import com.campus.mastermeme.core.presentation.ui.theme.MasterMemeTheme
import com.campus.mastermeme.core.presentation.ui.ObserveAsEvents
import dev.shreyaspatil.capturable.capturable
import dev.shreyaspatil.capturable.controller.rememberCaptureController

@Composable
fun EditScreenRoot(
    memeId: Int,
    viewModel: EditViewModel = org.koin.androidx.compose.koinViewModel(),
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    ObserveAsEvents(flow = viewModel.events) { event ->
        when (event) {
            is EditEvent.ErrorWhenSaving -> {
                Toast.makeText(context, R.string.error_when_saving, Toast.LENGTH_SHORT).show()
            }

            is EditEvent.SavedSuccessfully -> {
                Toast.makeText(context, R.string.saved_successfully, Toast.LENGTH_SHORT).show()
            }
        }
    }

    EditScreen(
        state = viewModel.state,
        memeId = memeId,
        onAction = { action ->
            when (action) {
                EditAction.OnLeaveEditor -> {
                    println("EditAction.OnLeaveEditor")
                    onBackClick()
                }

                else -> Unit
            }
            viewModel.onAction(action)
        }


    )
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun EditScreen(
    memeId: Int,
    state: EditState,
    onAction: (EditAction) -> Unit
) {


    val captureController = rememberCaptureController()

    val skipPartiallyExpanded by remember { mutableStateOf(true) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )

    val activity = LocalContext.current as ComponentActivity

    val context = LocalContext.current

    val storagePermissionResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            onAction(
                EditAction.OnPermissionResult(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    isGranted
                )
            )
            if (isGranted) {
                onAction(
                    EditAction.OnSaveMemeToDevice(
                        context,
                        "meme_${System.currentTimeMillis()}.png",
                        captureController
                    )
                )
            }
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                onBackClick = { onAction(EditAction.OnBackClick) }
            )

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
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.secondaryContainer),
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                ) {
                    if (!state.isClickText) {
                        DefaultBottomBar(
                            onAddTextClick = { onAction(EditAction.OnAddText) },
                            onUndoClick = { onAction(EditAction.OnUndo) },
                            onRedoClick = { onAction(EditAction.OnRedo) },
                            isUndoEnabled = state.isUndoEnabled,
                            isRedoEnabled = state.isRedoEnabled,
                            onSaveMemeClick = {
                                onAction(EditAction.OnSaveMemeDefaultBottomTab)
                            },
                        )
                    } else {
                        ChangeTextStylesBottomBar(
                            onCancelClick = { onAction(EditAction.OnCancelChangeTextBottomTab) },
                            onTextStyleClick = { onAction(EditAction.OnChangeFontClick) },
                            onTextSizeClick = { onAction(EditAction.OnChangeSizeClick) },
                            onTextColorClick = { onAction(EditAction.OnChangeColorClick) },
                            onSaveClick = { onAction(EditAction.OnSaveChangeTextBottomTab) },
                            isTextStyleSelected = state.isChangeFont,
                            isTextSizeSelected = state.isChangeSize,
                            isTextColorSelected = state.isChangeColor

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

                if (state.isSaveOrShareBottomSheetOpen) {
                    ModalBottomSheet(
                        sheetState = sheetState,
                        onDismissRequest = {
                            onAction(EditAction.OnDismissSaveOrShareMemeBottomSheet)
                        },
                        content = {
                            SaveOrShareBottomSheet(
                                onSaveMemeToDevice = {
                                    //sdk 29 and above doesn't need permission to save image
                                    if (SDK_INT < 29) {
                                        storagePermissionResultLauncher.launch(
                                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                                        )
                                    }

                                    onAction(
                                        EditAction.OnSaveMemeToDevice(
                                            context,
                                            "meme_${System.currentTimeMillis()}.png",
                                            captureController
                                        )
                                    )
                                },
                                onShareMeme = {
                                    onAction(
                                        EditAction.OnShareMeme(
                                            context,
                                            "meme_${System.currentTimeMillis()}.png",
                                            captureController
                                        )
                                    )
                                }
                            )
                        }


                    )
                }

                if (state.isBackClickPopup) {
                    LeaveEditorDialog(
                        onDismiss = { onAction(EditAction.OnDismissBackDialog) },
                        onLeave = { onAction(EditAction.OnLeaveEditor) },
                    )

                }

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
                    id = memeId,
                    texts = state.texts,
                    onPositionChange = { index, offset ->
                        onAction(EditAction.OnChangePositionText(index, offset))
                    },
                    onDoubleTap = { index ->
                        onAction(EditAction.OnDoubleTap(index))

                    },
                    onSelectText = { index ->
                        onAction(EditAction.OnClickText(index))
                    },
                    onDeleteText = { index ->
                        onAction(EditAction.OnDeleteText(index))
                    },
                    selectedTextIndex = state.selectedTextIndex,
                    modifier = Modifier
                        .size(300.dp)
                        .capturable(captureController)
                )

                state.visiblePermissionDialogQueue
                    .reversed()
                    .forEach { permission ->
                        PermissionDialog(
                            permissionTextProvider = when (permission) {
                                Manifest.permission.WRITE_EXTERNAL_STORAGE -> {
                                    StoragePermissionTextProvider()
                                }

                                else -> return@forEach
                            },
                            isPermanentlyDeclined = !shouldShowRequestPermissionRationale(
                                activity,
                                permission
                            ),
                            onDismiss = {
                                onAction(EditAction.OnDismissPermissionDialog)
                            },
                            onOkClick = {
                                onAction(EditAction.OnDismissPermissionDialog)
                                storagePermissionResultLauncher.launch(
                                    permission
                                )
                            },
                            onGoToAppSettingsClick = {
                                onAction(EditAction.OnDismissPermissionDialog)
                                activity.openAppSettings()
                            }
                        )
                    }
            }
        }
    )


}

fun Activity.openAppSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}


@Preview

@Composable

private fun EditScreenPreview() {

    MasterMemeTheme {

        EditScreen(

            state = EditState(),

            onAction = {},
            memeId = 1

        )

    }

}

