package com.campus.mastermeme.edit.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        containerColor = MaterialTheme.colorScheme.surface,
        title = { Text(stringResource(R.string.text), fontSize = 24.sp) },
        text = {
            TextField(
                value = editableText,
                onValueChange = { editableText = it },
                singleLine = true,
                colors = TextFieldDefaults.colors().copy(
                    focusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                    cursorColor = MaterialTheme.colorScheme.secondary,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                )
            )
        },
        confirmButton = {
            TextButton(onClick = {
                onSaveText(
                    selectedTextIndex,
                    editableText
                )
                onDismissDialog()
            }) {
                Text(stringResource(R.string.ok), color = MaterialTheme.colorScheme.secondary)
            }
        },
        shape = RoundedCornerShape(8.dp),
        dismissButton = {
            TextButton(onClick = { onDismissDialog() }) {
                Text(stringResource(R.string.cancel), color = MaterialTheme.colorScheme.secondary)
            }
        }
    )
}
