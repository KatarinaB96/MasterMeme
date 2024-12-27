package com.campus.mastermeme.edit.di

import com.campus.mastermeme.edit.presentation.EditViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val editModule = module {
    viewModelOf(
        ::EditViewModel
    )
}