package com.campus.mastermeme.core.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "available_memes")
data class AvailableMemeEntity(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    val name: String,
    val resId: Int
)