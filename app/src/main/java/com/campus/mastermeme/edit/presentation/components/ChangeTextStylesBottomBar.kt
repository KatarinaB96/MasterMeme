package com.campus.mastermeme.edit.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.campus.mastermeme.R
import com.campus.mastermeme.ui.theme.MasterMemeTheme

@Composable
fun ChangeTextStylesBottomBar(modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .fillMaxWidth()

    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround

        ) {
            Icon(
                painter = painterResource(id = R.drawable.cancel),
                contentDescription = stringResource(R.string.cancel),
                modifier = Modifier
                    .size(24.dp),
            )
            Image(
                painter = painterResource(id = R.drawable.text_style),
                contentDescription = stringResource(R.string.text_style),
                modifier = Modifier.size(48.dp)
                    .clickable {
                    //    isChangeFont = !isChangeFont
                    },
            )
            Image(
                painter = painterResource(id = R.drawable.text_size),
                contentDescription = stringResource(R.string.text_size),
                modifier = Modifier.size(48.dp)
                    .clickable {
                      //  isChangeFont = !isChangeFont
                    },
            )
            Image(
                painter = painterResource(id = R.drawable.text_color),
                contentDescription = stringResource(R.string.text_color),
                modifier = Modifier.size(48.dp)
                    .clickable {
                  //      isChangeFont = !isChangeFont
                    },
            )
            Icon(
                painter = painterResource(id = R.drawable.check),
                contentDescription = stringResource(R.string.save),
                modifier = Modifier
                    .size(24.dp),
            )
        }
    }


}

@Preview
@Composable
private fun ChangeTextStylesBottomBarPreview() {
    MasterMemeTheme {
        ChangeTextStylesBottomBar(

        )
    }

}