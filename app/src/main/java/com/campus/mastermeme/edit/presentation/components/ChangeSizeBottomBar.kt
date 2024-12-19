package com.campus.mastermeme.edit.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.campus.mastermeme.R
import com.campus.mastermeme.ui.theme.MasterMemeTheme

@Composable
fun ChangeSizeBottomBar(modifier: Modifier = Modifier) {

    Row(
        modifier = Modifier
            .fillMaxWidth().height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Icon(
            painter = painterResource(id = R.drawable.small_size),
            contentDescription = stringResource(R.string.small_size),
            modifier = Modifier
                .size(24.dp),
        )

        Slider(
            value = 0.5f,
            onValueChange = { /*TODO*/ },
            modifier = Modifier.size(200.dp)
        )

        Icon(
            painter = painterResource(id = R.drawable.big_size),
            contentDescription = stringResource(R.string.big_size),
            modifier = Modifier
                .size(24.dp),
        )

    }
}

@Preview
@Composable
private fun ChangeSizeBottomBarPreview() {
    MasterMemeTheme {
        ChangeSizeBottomBar()
    }
}