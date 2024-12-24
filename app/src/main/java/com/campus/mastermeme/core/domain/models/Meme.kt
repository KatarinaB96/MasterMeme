package com.campus.mastermeme.core.domain.models

data class Meme(
    val id: Int,
    val name: String,
    val imageUri: Int,
    val isFavorite: Boolean,
    val createdAt: Long
)
