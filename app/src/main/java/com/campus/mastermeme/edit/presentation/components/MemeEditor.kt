package com.campus.mastermeme.edit.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.campus.mastermeme.R
import com.campus.mastermeme.edit.presentation.model.MemeText

@Composable
fun MemeEditor(
    texts: List<MemeText>,
    onPositionChange: (Int, Offset) -> Unit = { _, _ -> },
    onDoubleTap: (Int) -> Unit = {},
    onSelectText: (Int) -> Unit = {},
    onDeleteText: (Int) -> Unit = {},
    selectedTextIndex: Int = -1,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.background(Color.Red)
    ) {
        var imageWidth by remember { mutableFloatStateOf(0f) }
        var imageHeight by remember { mutableFloatStateOf(0f) }

        Box {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .onGloballyPositioned { layoutCoordinates ->
                        imageWidth = layoutCoordinates.size.width.toFloat()
                        imageHeight = layoutCoordinates.size.height.toFloat()
                    },
                contentScale = ContentScale.Crop
            )

            texts.forEachIndexed { index, memeText ->
                DraggableText(
                    index = index,
                    text = memeText,
                    onDoubleTap = { onDoubleTap(index) },
                    onDragEnd = { newPosition -> onPositionChange(index, newPosition) },
                    onSelectText = { onSelectText(index) },
                    isSelected = selectedTextIndex == index,
                    onDeleteText = { onDeleteText(index) },
                    parentWidth = imageWidth,
                    parentHeight = imageHeight
                )
            }
        }
    }
}

@Composable
fun DraggableText(
    index: Int,
    text: MemeText,
    onDragEnd: (Offset) -> Unit,
    onDoubleTap: (Int) -> Unit,
    onSelectText: (Int) -> Unit,
    onDeleteText: (Int) -> Unit,
    isSelected: Boolean,
    parentWidth: Float,
    parentHeight: Float
) {
    var offset by remember { mutableStateOf(text.position) }
    var textSize by remember { mutableStateOf(IntSize(0, 0)) }

    LaunchedEffect(text.position) {
        offset = text.position
    }
    Box(
        modifier = Modifier
            .offset { IntOffset(offset.x.toInt(), offset.y.toInt()) }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        onDragEnd(offset)
                    }
                ) { change, dragAmount ->
                    change.consume()
                    val newX = (offset.x + dragAmount.x)
                        .coerceIn(
                            0f,
                            parentWidth - textSize.width
                        )
                    val newY = (offset.y + dragAmount.y)
                        .coerceIn(0f, parentHeight - textSize.height) // Text

                    offset = Offset(newX, newY)
                }

            }
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        onDoubleTap(index)
                    },
                    onTap = {
                        onSelectText(index)
                    })
            }
            .padding(0.dp)


    ) {

        OutlinedText(
            text = text.text,
            fontSize = text.textSize.sp,
            fontFamily = text.textFont,
            color = text.textColor,
            modifier = Modifier
                .align(Alignment.Center)
                .border(
                    width = if (isSelected) 1.dp else 0.dp,
                    color = if (isSelected) Color.White else Color.Transparent,
                    shape = RoundedCornerShape(8.dp)
                )
                .onGloballyPositioned { layoutCoordinates ->
                    textSize = layoutCoordinates.size
                }
                .padding(8.dp)

        )
        if (isSelected) {
            Image(
                painter = painterResource(id = R.drawable.red_cancel_svg),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(12.dp)
                    .clickable {
                        onDeleteText(index)
                    }
            )
        }

    }


}

@Composable
fun OutlinedText(
    text: String, fontSize: TextUnit = 32.sp,
    color: Color,
    fontFamily: FontFamily,
    modifier: Modifier
) {
    Text(
        text = text,
        style = TextStyle(
            fontFamily = fontFamily,
            color = Color.Black,
            fontSize = fontSize,
            drawStyle = Stroke(
                width = 4f
            )
        ),
        modifier = modifier
    )
    Text(
        text = text,
        fontSize = fontSize,
        color = color,
        fontFamily = fontFamily,
        modifier = modifier
    )
}

@Preview(showBackground = false)
@Composable
fun OutlinedTextPreview() {
    OutlinedText(
        text = "Outline Text",
        color = Color.Black,
        fontFamily = FontFamily.Serif,
        modifier = Modifier
            .size(200.dp)
            .padding(16.dp)

    )
}
