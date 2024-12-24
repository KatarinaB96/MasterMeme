package com.campus.mastermeme.edit.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.campus.mastermeme.R
import com.campus.mastermeme.core.presentation.ui.theme.MasterMemeTheme

@Composable
fun DefaultBottomBar(onAddTextClick : () -> Unit,modifier: Modifier = Modifier) {
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
            shape = RoundedCornerShape(8.dp),
            onClick = onAddTextClick,
            modifier = Modifier
                .wrapContentWidth()
                .padding(8.dp)
        ) {
            Text(
                text = "Add Text",
            )
        }
        Button(
            shape = RoundedCornerShape(8.dp),
            onClick = { /*TODO*/ },
        ) {
            Text(
                text = "Save Meme",

                )
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