package com.campus.mastermeme.core.domain.models

data class Meme(
    val id: Int?,
    val name: String,
    val imageUri: String,
    val isFavorite: Boolean,
    val createdAt: Long
)
