package com.campus.mastermeme.edit.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.campus.mastermeme.R
import com.campus.mastermeme.ui.theme.DarkSurfaceContainerHigh
import com.campus.mastermeme.ui.theme.MasterMemeTheme

@Composable
fun ChangeTextStylesBottomBar(
    onCancelClick: () -> Unit = {},
    onTextStyleClick: () -> Unit = {},
    onTextSizeClick: () -> Unit = {},
    onTextColorClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
    isTextStyleSelected: Boolean = false,
    isTextSizeSelected: Boolean = false,
    isTextColorSelected: Boolean = false,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondaryContainer),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround

    ) {
        Icon(
            painter = painterResource(id = R.drawable.cancel),
            contentDescription = stringResource(R.string.cancel),
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    onCancelClick()
                },
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer),
        ) {
            Image(
                painter = painterResource(id = R.drawable.text_style_button),
                contentDescription = stringResource(R.string.text_style),
                modifier = Modifier
                    .size(48.dp)
                    .clickable {
                        onTextStyleClick()
                    }

                    .background(
                        if (isTextStyleSelected) DarkSurfaceContainerHigh else Color.Transparent,
                        shape = RoundedCornerShape(12.dp)
                    )

            )
            Image(
                painter = painterResource(id = R.drawable.text_size_button),
                contentDescription = stringResource(R.string.text_size),
                modifier = Modifier
                    .size(48.dp)
                    .clickable {
                        onTextSizeClick()
                    }
                    .background(
                        if (isTextSizeSelected) DarkSurfaceContainerHigh else Color.Transparent,
                        shape = RoundedCornerShape(12.dp)
                    ),
            )
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clickable {
                        onTextColorClick()
                    }
                    .background(
                        if (isTextColorSelected) DarkSurfaceContainerHigh else Color.Transparent,
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.color_palette),
                    contentDescription = stringResource(R.string.text_color),
                    modifier = Modifier
                        .size(36.dp),
                )
            }
        }


        Icon(
            painter = painterResource(id = R.drawable.check),
            contentDescription = stringResource(R.string.save),
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    onSaveClick()
                },
        )
    }


}

@Preview
@Composable
private fun ChangeTextStylesBottomBarPreview() {
    MasterMemeTheme {
        ChangeTextStylesBottomBar()
    }

}