package com.campus.mastermeme.core.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memes")
data class MemeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val imageUri: String,
    val isFavorite: Boolean = false,
    val timestamp: Long
)