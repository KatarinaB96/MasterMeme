package com.campus.mastermeme.core

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object MemeGraph : Route

    @Serializable
    data object MemeList : Route

    @Serializable
    data class MemeDetail(val memeId: Int) : Route

}