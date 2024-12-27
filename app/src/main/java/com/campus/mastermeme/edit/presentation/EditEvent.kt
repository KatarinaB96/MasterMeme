package com.campus.mastermeme.edit.presentation

sealed interface EditEvent {
    data object SavedSuccessfully : EditEvent
    data object ErrorWhenSaving : EditEvent
}