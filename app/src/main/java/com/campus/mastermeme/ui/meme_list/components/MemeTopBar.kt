package com.campus.mastermeme.ui.meme_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.campus.mastermeme.R
import com.campus.mastermeme.ui.theme.OnSurface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemeTopBar(
    inSelectionMode: Boolean,
    selectedIdsCount: Int = 0,
    selectedOption: String = "",
    imageListNotEmpty: Boolean = true,
    expanded: Boolean = false,
    onCloseSelection: () -> Unit = {},
    onDeleteSelection: () -> Unit = {},
    onDropdownClick: () -> Unit = {},
    onDismissDropdown: () -> Unit = {},
    onOptionSelect: (String) -> Unit = {}
) {
    val context = LocalContext.current

    if (inSelectionMode) {
        TopAppBar(
            title = {
                Text(
                    text = "$selectedIdsCount",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            },
            navigationIcon = {
                IconButton(onClick = onCloseSelection) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        tint = MaterialTheme.colorScheme.secondary,
                        contentDescription = stringResource(R.string.close_selection)
                    )
                }
            },
            actions = {
                IconButton(onClick = {

                }) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        tint = MaterialTheme.colorScheme.secondary,
                        contentDescription = stringResource(R.string.share)
                    )
                }
                IconButton(onClick = onDeleteSelection) {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        tint = MaterialTheme.colorScheme.secondary,
                        contentDescription = stringResource(R.string.delete)
                    )
                }
            },

            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        )
    } else {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.your_memes),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.headlineLarge,
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ),
            actions = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = if (imageListNotEmpty) Modifier.clickable { onDropdownClick() } else Modifier
                ) {
                    Text(
                        text = selectedOption,
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Center,
                        color = if (imageListNotEmpty) MaterialTheme.colorScheme.secondary else OnSurface.copy(alpha = 0.5f)
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = stringResource(R.string.dropdown),
                        tint = if (imageListNotEmpty)  MaterialTheme.colorScheme.secondary else OnSurface.copy(alpha = 0.5f),
                        modifier = Modifier.size(20.dp)
                    )
                    if (imageListNotEmpty) {
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = onDismissDropdown
                        ) {
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.favourites_first)) },
                                onClick = { onOptionSelect(context.getString(R.string.favourites_first)) }
                            )
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.newest_first)) },
                                onClick = { onOptionSelect(context.getString(R.string.newest_first)) }
                            )
                        }
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MemeTopBarPreview() {
    Column {
        // Preview in selection mode
        MemeTopBar(
            inSelectionMode = true,
            selectedIdsCount = 3,
            onCloseSelection = {},
            onDeleteSelection = {}
        )

        Spacer(modifier = Modifier.height(56.dp))

        // Preview in default mode
        MemeTopBar(
            inSelectionMode = false,
            selectedOption = "Favourites first",
            imageListNotEmpty = true,
            expanded = false,
            onDropdownClick = {},
            onDismissDropdown = {},
            onOptionSelect = {}
        )
    }
}
