package com.campus.mastermeme.edit.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class EditViewModel : ViewModel() {
    var state by mutableStateOf(EditState())
        private set
}