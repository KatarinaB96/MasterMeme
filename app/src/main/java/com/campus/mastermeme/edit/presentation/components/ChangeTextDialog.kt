package com.campus.mastermeme.edit.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.campus.mastermeme.R

@Composable
fun ChangeTextDialog(
    onDismissDialog: () -> Unit,
    onSaveText: (Int, String) -> Unit,
    selectedTextIndex: Int,
    text: String,
) {
    var editableText by remember { mutableStateOf(text) }
    AlertDialog(
        onDismissRequest = {
            onDismissDialog()
        },
        title = { Text("Metin Düzenle") },
        text = {
            TextField(
                value = editableText,
                onValueChange = { editableText = it },
                singleLine = true
            )
        },
        confirmButton = {
            TextButton(onClick = {
                onSaveText(
                    selectedTextIndex,
                    editableText
                )

            }) {
                Text("Kaydet")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissDialog() }) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
}
