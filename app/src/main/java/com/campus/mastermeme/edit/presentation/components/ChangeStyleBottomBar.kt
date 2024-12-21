package com.campus.mastermeme.edit.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.campus.mastermeme.R
import com.campus.mastermeme.ui.theme.MasterMemeTheme
import com.campus.mastermeme.ui.theme.provider

@Composable
fun ChangeStyleBottomBar(
    onChangeFontClick: (FontFamily) -> Unit,
    modifier: Modifier = Modifier
) {
    val fontList = listOf(
        R.drawable.frame_42,
        R.drawable.frame_43,
        R.drawable.frame_44,
        R.drawable.frame_45,
        R.drawable.frame_46,
        R.drawable.frame_42,
        R.drawable.frame_43,
        R.drawable.frame_44,
        R.drawable.frame_45,
        R.drawable.frame_46,
        R.drawable.frame_42,
        R.drawable.frame_43,
        R.drawable.frame_44,
        R.drawable.frame_45,
        R.drawable.frame_46,
    )

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
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .horizontalScroll(scrollState)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {


        fontFamilyList.forEachIndexed { index, font ->
            Text(
                text = "GOOD",
                fontFamily = font,
                color = Color.White,
                modifier = Modifier
                    .padding(horizontal = 2.dp, vertical = 8.dp)
                    .clickable {
                        onChangeFontClick(fontFamilyList[index])
                    }
            )
        }


        /*  fontList.forEach { font ->
              Image(
                  painter = painterResource(id = font),
                  contentDescription = "My Local Image",
                  modifier = Modifier.size(64.dp),
                  contentScale = ContentScale.Crop
              )
          }*/

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