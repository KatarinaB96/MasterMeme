package com.campus.mastermeme.core.domain

import com.campus.mastermeme.core.domain.models.AvailableMeme
import com.campus.mastermeme.core.domain.models.Meme
import kotlinx.coroutines.flow.Flow

interface MemesRepository {
    fun getMemes(): Flow<List<Meme>>
    suspend fun addOrUpdateMeme(meme: Meme)
    suspend fun deleteMemes(memes: List<Meme>)
    suspend fun getAvailableMemes(): Flow<List<AvailableMeme>>
    suspend fun markAsFavorite(meme: Meme)
    suspend fun removeFromFavorites(meme: Meme)
    fun searchAvailableMemesByName(searchQuery: String): Flow<List<AvailableMeme>>
}