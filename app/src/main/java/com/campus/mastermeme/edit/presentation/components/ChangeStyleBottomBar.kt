package com.campus.mastermeme.edit.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.campus.mastermeme.ui.theme.MasterMemeTheme
import com.campus.mastermeme.ui.theme.provider

@Composable
fun ChangeStyleBottomBar(
    onChangeFontClick: (FontFamily) -> Unit,
    modifier: Modifier = Modifier
) {

    val robotoFont = FontFamily(
        Font(
            googleFont = GoogleFont("Roboto"),
            fontProvider = provider
        )
    )
    val montserratFont = FontFamily(
        Font(
            googleFont = GoogleFont("Montserrat"),
            fontProvider = provider
        )
    )
    val poppinsFont = FontFamily(
        Font(
            googleFont = GoogleFont("Poppins"),
            fontProvider = provider
        )
    )
    val impactFont = FontFamily(
        Font(
            googleFont = GoogleFont("Impact"),
            fontProvider = provider
        )
    )

    val fontFamilyList = listOf(
        impactFont,
        robotoFont,
        montserratFont,
        poppinsFont,
    )
    val fontNameList = listOf(
        "Impact",
        "Roboto",
        "Montserrat",
        "Poppins",
    )
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .horizontalScroll(scrollState)
            .padding(18.dp)
            .background(MaterialTheme.colorScheme.secondaryContainer),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {


        fontFamilyList.forEachIndexed { index, font ->
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "GOOD",

                    color = Color.White,
                    style = TextStyle.Default.copy(
                        fontSize = 24.sp,
                        fontFamily = font,
                        drawStyle = Stroke(
                            miter = 10f,
                            width = 5f,
                            join = StrokeJoin.Round
                        )
                    ),
                    modifier = Modifier
                        .padding(horizontal = 2.dp, vertical = 8.dp)
                        .clickable {
                            onChangeFontClick(fontFamilyList[index])
                        }
                )
                Text(
                    fontNameList[index],
                    color = Color.White,
                    fontSize = 12.sp
                )
            }

        }

    }

}

@Preview
@Composable
private fun ChangeStyleBottomBarPreview() {
    MasterMemeTheme {
        ChangeStyleBottomBar(
            onChangeFontClick = {}
        )
    }

}