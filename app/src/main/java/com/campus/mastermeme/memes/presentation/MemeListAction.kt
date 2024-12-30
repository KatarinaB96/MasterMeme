package com.campus.mastermeme.memes.presentation

import com.campus.mastermeme.core.domain.models.Meme

sealed interface MemeListAction {
    data object OnOrderByFavoriteClick : MemeListAction
    data object OnOrderByNewestClick : MemeListAction
    data class OnSelectionMode(val meme: Meme) : MemeListAction
    data class OnSelectionChange(val updatedSelection: List<Meme>) : MemeListAction
    data object ExitSelectionMode : MemeListAction
    data class DeleteMemeList(val memes: List<Meme>) : MemeListAction
    data class OnShareSelectedMemes(val memes: List<Meme>) : MemeListAction
    data class OnFavoriteToggle(val meme: Meme) : MemeListAction

    data class OnSortTypeChanged(val sortType: SortType) : MemeListAction
    data class OnSearchQueryChange(val query: String) : MemeListAction

    data class OnMemeClick(val memeId: Int) : MemeListAction
}