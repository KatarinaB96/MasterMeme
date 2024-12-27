package com.campus.mastermeme.core.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface MemeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAvailableMemes(memes: List<AvailableMemeEntity>)

    @Query("SELECT * FROM available_memes")
    fun getAvailableMemes(): Flow<List<AvailableMemeEntity>>

    @Upsert
    suspend fun upsertMeme(meme: MemeEntity)

    @Query("DELETE FROM memes WHERE id IN (:ids)")
    suspend fun deleteMemes(ids: List<Int>)

    @Query("SELECT * FROM memes")
    fun getMemes(): Flow<List<MemeEntity>>

    @Query("SELECT * FROM available_memes WHERE name LIKE '%' || :searchQuery || '%'")
    fun searchAvailableMemesByName(searchQuery: String): Flow<List<AvailableMemeEntity>>

}