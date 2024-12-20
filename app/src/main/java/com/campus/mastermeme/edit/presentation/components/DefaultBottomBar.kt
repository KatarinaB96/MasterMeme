package com.campus.mastermeme.edit.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.campus.mastermeme.R
import com.campus.mastermeme.ui.theme.GradientColor2
import com.campus.mastermeme.ui.theme.MasterMemeTheme
import com.campus.mastermeme.ui.theme.PrimaryContainer

@Composable
fun DefaultBottomBar(onAddTextClick: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround

    ) {


        Row(
            modifier = Modifier

        ) {
            Icon(
                painter = painterResource(id = R.drawable.undo),
                contentDescription = stringResource(R.string.back),
            )

            Icon(
                painter = painterResource(id = R.drawable.redo),
                contentDescription = stringResource(R.string.back),
                tint = Color.Black,
            )
        }

        OutlinedButton(
            border = BorderStroke(
                width = 1.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        PrimaryContainer,
                        GradientColor2
                    )
                )
            ),
            shape = RoundedCornerShape(8.dp),
            onClick = onAddTextClick,
            modifier = Modifier
                .wrapContentWidth()
                .padding(8.dp)
        ) {
            Text(
                text = "Add Text",
                style = TextStyle(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            PrimaryContainer,
                            GradientColor2
                        )
                    )
                )
            )
        }
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            onClick = { /*TODO*/ },
        ) {
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                PrimaryContainer,
                                GradientColor2
                            )
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Save Meme",
                    color = MaterialTheme.colorScheme.onPrimary

                )
            }
        }

    }

}

@Preview
@Composable
private fun DefaultBottomBarPreview() {
    MasterMemeTheme {
        DefaultBottomBar(
            onAddTextClick = { /*TODO*/ }
        )
    }

}