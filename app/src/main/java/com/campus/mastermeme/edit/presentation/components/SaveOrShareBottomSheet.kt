package com.campus.mastermeme.edit.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.campus.mastermeme.R
import com.campus.mastermeme.ui.theme.MasterMemeTheme


@Composable
fun SaveOrShareBottomSheet(
    onSaveMemeToDevice: () -> Unit,
    onShareMeme: () -> Unit,
    modifier: Modifier = Modifier) {


    Column {
        BottomSheetListItem(
            iconId = R.drawable.download,
            title = stringResource(id = R.string.save_to_device),
            desc = stringResource(id = R.string.save_to_device_desc),
            onClick = {
                onSaveMemeToDevice()
            }
        )
        BottomSheetListItem(
            iconId = R.drawable.share,
            title = stringResource(id = R.string.share_the_meme),
            desc = stringResource(id = R.string.share_the_meme_desc),
            onClick = {
                onShareMeme()
            }
        )


    }

}

@Composable
fun BottomSheetListItem(
    iconId: Int,
    title: String,
    desc: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                onClick()
            }) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = title,
            tint = MaterialTheme.colorScheme.secondary,
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = title,
                fontSize = 18.sp,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.W700
            )
            Text(
                text = desc,
                fontSize = 12.sp,
                fontWeight = FontWeight.W400,
                color = MaterialTheme.colorScheme.outline
            )
        }
    }


}


@Preview
@Composable
private fun SaveOrShareBottomSheetPreview() {
    MasterMemeTheme {
        BottomSheetListItem(
            iconId = R.drawable.download,
            title = stringResource(id = R.string.save_to_device),
            desc = stringResource(id = R.string.save_to_device_desc),
            onClick = {}
        )
    }
}