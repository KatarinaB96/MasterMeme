package com.campus.mastermeme.ui.meme_list.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.campus.mastermeme.R
import com.campus.mastermeme.ui.theme.BottomBorderColor

@Composable
fun DeleteMemesDialog(
    memesCount: Int,
    onCancel: () -> Unit,
    onDelete: () -> Unit
) {
    Dialog(onDismissRequest = onCancel) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(1.dp, BottomBorderColor)
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 24.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
                    .width(380.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = pluralStringResource(R.plurals.dialog_title, memesCount, memesCount),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.W500,
                    fontSize = 24.sp,
                    lineHeight = 28.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.dialog_text),
                    color = Color.White,
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp,
                    lineHeight = 21.86.sp,
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onCancel) {
                        Text(
                            text = stringResource(R.string.cancel),
                            color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.W700
                        )
                    }
                    TextButton(onClick = onDelete) {
                        Text(
                            text = stringResource(R.string.delete),
                            color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.W700
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DeleteMemesDialogPreview() {
    DeleteMemesDialog(
        memesCount = 5,
        onCancel = {},
        onDelete = {}
    )
}
