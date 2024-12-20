package com.campus.mastermeme.edit.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.campus.mastermeme.R
import com.campus.mastermeme.edit.presentation.components.ChangeColorBottomBar
import com.campus.mastermeme.edit.presentation.components.ChangeSizeBottomBar
import com.campus.mastermeme.edit.presentation.components.ChangeStyleBottomBar
import com.campus.mastermeme.edit.presentation.components.ChangeTextStylesBottomBar
import com.campus.mastermeme.edit.presentation.components.DefaultBottomBar
import com.campus.mastermeme.edit.presentation.components.TopAppBar
import com.campus.mastermeme.ui.theme.MasterMemeTheme

@Composable

fun EditScreenRoot(
    viewModel: EditViewModel = org.koin.androidx.compose.koinViewModel()
) {

    EditScreen(
        state = viewModel.state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun EditScreen(
    state: EditState,
    onAction: (EditAction) -> Unit
) {


    Scaffold(
        topBar = {
            TopAppBar()

        },
        bottomBar = {
            Column {
                if (state.isChangeFont) {
                    ChangeStyleBottomBar()
                }
                if (state.isChangeSize) {
                    ChangeSizeBottomBar()
                }
                if (state.isChangeColor) {
                    ChangeColorBottomBar()
                }
                BottomAppBar(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    if (!state.isClickText) {
                        DefaultBottomBar(
                            onAddTextClick = { onAction(EditAction.AddText) },
                        )
                    } else {
                        ChangeTextStylesBottomBar(

                        )
                    }
                }
            }

        },

        content = {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),

                ) {
                Image(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    contentDescription = "Meme",
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                )
            }
        }
    )
}




@Preview

@Composable

private fun EditScreenPreview() {

    MasterMemeTheme {

        EditScreen(

            state = EditState(),

            onAction = {}

        )

    }

}

