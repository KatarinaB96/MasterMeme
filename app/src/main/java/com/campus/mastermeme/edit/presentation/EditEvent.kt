package com.campus.mastermeme.edit.presentation

sealed interface EditEvent {
    data object SavedSuccessfully : EditEvent //for navigating to login screen
    data object ErrorWhenSaving : EditEvent
}