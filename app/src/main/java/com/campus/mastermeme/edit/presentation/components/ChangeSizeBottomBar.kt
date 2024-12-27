package com.campus.mastermeme.edit.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.campus.mastermeme.R
import com.campus.mastermeme.core.presentation.ui.theme.MasterMemeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeSizeBottomBar(
    textSize: Float,
    onChangeSize: (Float) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var value by remember { mutableFloatStateOf(textSize) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(MaterialTheme.colorScheme.secondaryContainer),
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
            value = value,
            onValueChange = { newValue ->
                value = newValue
                onChangeSize(newValue)
            },
            valueRange = 10f..48f,
            modifier = Modifier.size(200.dp),
            thumb = {
                Box(
                    modifier = modifier
                        .size(24.dp)
                        .background(
                            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(24.dp * 0.6f)
                            .background(
                                color = MaterialTheme.colorScheme.secondary,
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
        ChangeSizeBottomBar(
            textSize = 24f,
            onChangeSize = {}
        )
    }
}