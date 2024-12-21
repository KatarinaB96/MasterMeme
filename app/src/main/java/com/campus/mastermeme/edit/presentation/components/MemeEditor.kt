package com.campus.mastermeme.edit.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.campus.mastermeme.R
import com.campus.mastermeme.edit.presentation.model.MemeText

@Composable
fun MemeEditor(
    texts: List<MemeText>,
    onPositionChange: (Int, Offset) -> Unit,
    onDoubleTap: () -> Unit,
    onSelectText: (Int) -> Unit,
    selectedTextIndex: Int
) {
    Box(modifier = Modifier.size(300.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        texts.forEachIndexed { index, memeText ->
            DraggableText(
                index = index,
                text = memeText,

                onDoubleTap = onDoubleTap,
                onDragEnd = { newPosition -> onPositionChange(index, newPosition) },
                onSelectText = { onSelectText(index) },
                isSelected = selectedTextIndex == index

            )
        }
    }
}

@Composable
fun DraggableText(
    index: Int,
    text: MemeText,
    onDragEnd: (Offset) -> Unit,
    onDoubleTap: () -> Unit,
    onSelectText: (Int) -> Unit,
    isSelected: Boolean
) {
    var offset by remember { mutableStateOf(text.position) }
    Text(
        text = text.text,
        color = text.textColor,
        fontSize = text.textSize.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .offset { IntOffset(offset.x.toInt(), offset.y.toInt()) }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offset = Offset(offset.x + dragAmount.x, offset.y + dragAmount.y)
                }
            }
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        onDoubleTap()
                    },
                    onTap = {
                        onSelectText(index)
                    })
            }
            .background(Color.Black.copy(alpha = 0.5f))
            .border(
                if (isSelected) 2.dp else 0.dp,
                if (isSelected) Color.Red else Color.Transparent
            )
    )

    LaunchedEffect(offset) {
        onDragEnd(offset)
    }
}