package com.campus.mastermeme.ui.meme_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.campus.mastermeme.R
import com.campus.mastermeme.ui.theme.BottomBorderColor
import com.campus.mastermeme.ui.theme.CursorColor
import com.campus.mastermeme.ui.theme.SchemesOutline

@Composable
fun SearchTextField() {
    var textValue by remember { mutableStateOf("") }
    val imageList = emptyList<Int>()
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    Column {
        TextField(
            value = textValue,
            onValueChange = { newValue ->
                textValue = newValue
            },

            placeholder = {
                Text(
                    stringResource(R.string.search_input),
                    color = Color.Gray
                )
            },

            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    drawLine(
                        color = BottomBorderColor,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = 1f
                    )
                }
                .focusRequester(focusRequester),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),

            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = CursorColor,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),

            leadingIcon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    tint = MaterialTheme.colorScheme.secondary
                )
            },
            trailingIcon = {
                if (textValue.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            textValue = ""
                        }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(R.string.clear_text),
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (imageList.isEmpty()) {
            Text(
                stringResource(R.string.no_memes_found),
                color = SchemesOutline,
                fontWeight = FontWeight.W400,
                fontSize = 12.sp,
                lineHeight = 16.39.sp,
            )
        } else {
            Text(
                stringResource(R.string.number_of_templates, imageList.size),
                color = SchemesOutline,
                fontWeight = FontWeight.W400,
                fontSize = 12.sp,
                lineHeight = 16.39.sp
            )
        }
    }
}

@Preview
@Composable
fun SearchTextFieldPreview() {
    SearchTextField()
}
