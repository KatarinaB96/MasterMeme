package com.campus.mastermeme.edit.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.campus.mastermeme.R
import com.campus.mastermeme.ui.theme.MasterMemeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeSizeBottomBar(modifier: Modifier = Modifier) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Icon(
            painter = painterResource(id = R.drawable.small_size),
            contentDescription = stringResource(R.string.small_size),
            modifier = Modifier
                .size(32.dp),
        )

        Slider(
            value = 0.5f,
            onValueChange = { /*TODO*/ },
            modifier = Modifier.size(200.dp),
            thumb = {
                Box(
                    modifier = modifier
                        .size(24.dp)  // Dış dairenin boyutu
                        .background(
                            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f),  // Alpha değeri düşük dış daire
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(24.dp * 0.6f)  // İç dairenin boyutu (dış dairenin %60'ı)
                            .background(
                                color = MaterialTheme.colorScheme.secondary,  // Tam opaklıkta iç daire
                                shape = CircleShape
                            )
                    )
                }

            },
            track = {

                Box(
                    modifier = Modifier
                        .height(2.dp)
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.secondary
                        )
                )
            }
        )

        Icon(
            painter = painterResource(id = R.drawable.big_size),
            contentDescription = stringResource(R.string.big_size),
            modifier = Modifier
                .size(32.dp),
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