package com.campus.mastermeme.core.data

import android.net.Uri
import com.campus.mastermeme.core.data.local.MemeDao
import com.campus.mastermeme.core.data.local.toDomainModel
import com.campus.mastermeme.core.data.local.toEntity
import com.campus.mastermeme.core.domain.MemesRepository
import com.campus.mastermeme.core.domain.models.AvailableMeme
import com.campus.mastermeme.core.domain.models.Meme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MemesRepositoryImpl(private val dao: MemeDao) : MemesRepository {

    override suspend fun getAvailableMemes(): Flow<List<AvailableMeme>> {
        return dao.getAvailableMemes().map { entities ->
            entities.map {
                it.toDomainModel()
            }
        }
    }

    override fun getMemes(): Flow<List<Meme>> {
        return dao.getMemes()
            .map { entities -> entities.map { it.toDomainModel() } }
    }

    override suspend fun addOrUpdateMeme(meme: Meme) {
        dao.upsertMeme(meme.toEntity())
    }

    override suspend fun deleteMemes(memes: List<Meme>) {
        return dao.deleteMemes(memes.mapNotNull {
            it.id
        })
    }

    override suspend fun markAsFavorite(meme: Meme) {
        addOrUpdateMeme(meme.copy(isFavorite = true))
    }

    override suspend fun removeFromFavorites(meme: Meme) {
        addOrUpdateMeme(meme.copy(isFavorite = false))
    }

    override fun searchAvailableMemesByName(searchQuery: String): Flow<List<AvailableMeme>> {
        return dao.searchAvailableMemesByName(searchQuery).map { it.map { it.toDomainModel() } }
    }

    override suspend fun getMemeUri(meme: Meme): Uri {
        return Uri.parse(meme.imageUri.toString())
    }
}
