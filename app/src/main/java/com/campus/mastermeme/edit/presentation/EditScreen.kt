package com.campus.mastermeme.edit.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.campus.mastermeme.R
import com.campus.mastermeme.ui.theme.MasterMemeTheme

@Composable

fun EditScreenRoot(

//    viewModel: EditViewModel = org.koin.androidx.compose.koinViewModel()

) {

    EditScreen(

        //   state = viewModel.state,

        //  onAction = viewModel::onAction

    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable

private fun EditScreen(

    //state: EditState,

    // onAction: (EditAction) -> Unit

) {

    Scaffold(

        topBar = {

            CenterAlignedTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = stringResource(R.string.back),
                        tint = Color.Black
                    )
                },

                title = { Text("New Meme", textAlign = TextAlign.Center) }
            )


        },
        bottomBar = {
            BottomAppBar {
                Text("Bottom Bar")
            }


        },

        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {

                Text(

                    text = "Edit Screen",

                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)

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

            //  state = EditState(),

            //onAction = {}

        )

    }

}