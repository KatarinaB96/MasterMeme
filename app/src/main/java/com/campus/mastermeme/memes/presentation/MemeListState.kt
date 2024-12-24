package com.campus.mastermeme.memes.presentation

import com.campus.mastermeme.core.domain.models.AvailableMeme
import com.campus.mastermeme.core.domain.models.Meme

data class MemeListState(
    val searchQuery: String = "",
    val searchResults: List<AvailableMeme> = emptyList(),
    val errorMessage: String? = null,
    val memes: List<Meme> = emptyList(),
    val availableMeme: List<AvailableMeme> = emptyList(),
    val sortType: SortType = SortType.IS_FAVORITE,
    val inSelectionMode: Boolean = false,
    val selectedMemes: List<Meme> = emptyList()
)
