package com.campus.mastermeme.memes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.campus.mastermeme.core.domain.MemesRepository
import com.campus.mastermeme.core.domain.models.Meme
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MemeListViewModel(
    private val repository: MemesRepository
) : ViewModel() {
    private val _sortType = MutableStateFlow(SortType.IS_FAVORITE)
    private val _state = MutableStateFlow(MemeListState())
    private val allMemes = mutableListOf<Meme>()
    private var searchJob: Job? = null

    val state = _state
        .onStart {
            fetchMemes()
            loadPrepopulatedMemes()
            observeSearchQuery()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    private fun loadPrepopulatedMemes() {
        viewModelScope.launch {
            repository.getAvailableMemes().collect { memes ->
                _state.value = _state.value.copy(availableMeme = memes)
            }
        }
    }

    fun onAction(action: MemeListAction) {
        when (action) {
            is MemeListAction.DeleteMemeList -> {
                viewModelScope.launch {
                    repository.deleteMemes(action.memes)
                }
                _state.value = _state.value.copy(
                    inSelectionMode = false,
                    selectedMemes = emptyList()
                )
            }

            is MemeListAction.OnSortTypeChanged -> {
                _sortType.value = action.sortType
                applySort(action.sortType)
            }


            is MemeListAction.OnFavoriteToggle -> {
                viewModelScope.launch {
                    if (action.meme.isFavorite) {
                        repository.removeFromFavorites(action.meme)
                    } else {
                        repository.markAsFavorite(action.meme)
                    }
                }
            }

            MemeListAction.OnOrderByFavoriteClick -> {
                onSortTypeChanged(SortType.IS_FAVORITE)
            }

            MemeListAction.OnOrderByNewestClick -> {
                onSortTypeChanged(SortType.NEWEST)
            }

            is MemeListAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(
                        searchQuery = action.query
                    )
                }
            }

            is MemeListAction.OnSelectionMode -> {
                _state.value = _state.value.copy(
                    inSelectionMode = true,
                    selectedMemes = _state.value.selectedMemes.toMutableList().apply {
                        if (!contains(action.meme)) add(action.meme)
                    }
                )
            }

            is MemeListAction.OnSelectionChange -> {
                _state.value = _state.value.copy(
                    selectedMemes = action.updatedSelection
                )
            }

            is MemeListAction.ExitSelectionMode -> {
                _state.value = _state.value.copy(
                    inSelectionMode = false,
                    selectedMemes = emptyList()
                )
            }

            is MemeListAction.OnShareSelectedMemes -> {
                viewModelScope.launch {
                    val selectedMemeUris = action.memes.map { meme ->
                        repository.getMemeUri(meme)
                    }
                    _state.value = _state.value.copy(sharedMemeUris = selectedMemeUris)
                }
            }
            else -> Unit
        }
    }

    private fun observeSearchQuery() {
        state
            .map { it.searchQuery }
            .distinctUntilChanged()
            .onEach { query ->
                when {
                    query.isBlank() -> _state.update {
                        it.copy(
                            searchResults = emptyList(),
                            errorMessage = null
                        )
                    }

                    query.isNotEmpty() -> {
                        searchJob?.cancel()
                        searchJob = searchMemes(query)
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun searchMemes(query: String): Job = viewModelScope.launch {
        try {
            repository.searchAvailableMemesByName(query).collect { results ->
                _state.update { it.copy(searchResults = results, errorMessage = null) }
            }
        } catch (e: Exception) {
            _state.update { it.copy(errorMessage = e.message) }
        }
    }

    private fun fetchMemes() {
        viewModelScope.launch {
            repository.getMemes().collect { fetchedMemes ->
                allMemes.clear()
                allMemes.addAll(fetchedMemes)
                applySort(_sortType.value)
            }
        }
    }

    private fun applySort(sortType: SortType) {
        _state.value = _state.value.copy(
            memes = sortMemes(allMemes, sortType),
            sortType = sortType
        )
    }

    private fun sortMemes(memes: List<Meme>, sortType: SortType): List<Meme> {
        return when (sortType) {
            SortType.IS_FAVORITE -> memes.sortedByDescending { it.createdAt }.sortedByDescending { it.isFavorite }
            SortType.NEWEST -> memes.sortedByDescending { it.createdAt }
        }
    }

    private fun onSortTypeChanged(newSortType: SortType) {
        _sortType.value = newSortType
    }
}

