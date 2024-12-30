package com.campus.mastermeme.edit.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.campus.mastermeme.R

@Composable
fun LeaveEditorDialog(
    onLeave: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        shape = RoundedCornerShape(8.dp),
        title = {
            Text(
                stringResource(R.string.leave_editor),
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        text = {
            Text(
                stringResource(R.string.leave_editor_message),
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onDismiss()
                    onLeave()
                },
                ) {
                Text(
                    stringResource(R.string.leave),
                    color = MaterialTheme.colorScheme.secondary,
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss()
                },
                ) {
                Text(
                    stringResource(R.string.cancel),
                    color = MaterialTheme.colorScheme.secondary,
                )
            }
        },
        modifier = modifier
    )

}